package org.dakralex.pricevista.parser

import com.aayushatharva.brotli4j.Brotli4jLoader
import com.aayushatharva.brotli4j.decoder.Decoder
import com.aayushatharva.brotli4j.decoder.DecoderJNI
import com.aayushatharva.brotli4j.decoder.DirectDecompress
import io.github.oshai.kotlinlogging.KotlinLogging
import org.dakralex.pricevista.contracts.dao.ArticleDao
import org.dakralex.pricevista.contracts.dao.BrandDao
import org.dakralex.pricevista.contracts.dao.CompanyDao
import org.dakralex.pricevista.contracts.database.PriceVistaRepository
import org.dakralex.pricevista.contracts.parser.JsonEntry
import org.dakralex.pricevista.contracts.parser.JsonParser
import org.dakralex.pricevista.entities.*
import org.dakralex.pricevista.entities.data.EArticleUnit
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.time.Instant
import java.util.*

private val logger = KotlinLogging.logger {}

abstract class StoreJsonParser<T : JsonEntry>(
    private val repo: PriceVistaRepository
) : JsonParser {

    abstract val store: Store

    open val storeDefaultBrand: Brand? = null

    abstract fun decodeJsonFromInputStream(inputStream: InputStream): Sequence<T>


    abstract fun parseInternalIdentifier(entry: T): String

    abstract fun parseBrandName(entry: T): String?

    abstract fun parseArticleFullName(entry: T): String

    abstract fun parseLongDescription(entry: T): String?

    open fun parseOriginCountry(entry: T): Country? = null

    open fun parseArticleUnit(entry: T): ArticleUnit =
        EArticleUnit.MISC_PIECE.unit

    open fun parseQuantity(entry: T): Double = 0.0

    open fun parseIsWeightable(entry: T): Boolean = false

    open fun parseImageUrls(entry: T): List<String> = listOf()

    abstract fun parseArticlePrice(entry: T): Long


    private fun parseArticleBrand(entry: T, brandDao: BrandDao): Brand? {
        val brandName = parseBrandName(entry) ?: ""
        val articleFullName = parseArticleFullName(entry)

        val brands = brandDao.list()

        val byArticleName = { brand: Brand ->
            articleFullName.contains(brand.company.shortName, true)
        }

        return if (brandName.isNotBlank()) {
            val byBrandName = { brand: Brand ->
                brand.company.shortName.equals(brandName, true)
            }

            brands.firstOrNull(byBrandName) ?: brands.firstOrNull(byArticleName)
        } else brands.firstOrNull(byArticleName)
    }

    private fun parseArticleName(entry: T, brand: Brand?): String {
        val articleFullName = parseArticleFullName(entry)
        val articleBrandName = brand?.company?.shortName ?: ""

        val cleanArticleName = articleFullName
            .replace(articleBrandName, "", true).trim()

        return cleanArticleName.ifBlank {
            articleFullName
        }
    }

    private fun parseEntry(inputStream: InputStream, timestamp: Instant) {
        // TODO This should be doable with abstraction, but I got an unresolvable
        //      exception I couldn't fix, see kotlinx.serialization#2537
        // TODO I wish for the future to use the sequence inline everywhere, but
        //      for that I would need to implement the entities to have fixed ids
        //      immediately or in-place (maybe lazy eval?)
        val entries =
            decodeJsonFromInputStream(inputStream).toList().asSequence()

        // Parse all unique companies and put them into the companies and brands repo
        // TODO Make interface to addBrands transitive to addCompanies
        val companies = mapCompaniesFromEntries(entries)
        val companyDao = repo.addCompanies(companies).commit() as CompanyDao
        val repoCompanies = companyDao.list()
        val brands = repoCompanies.map(Brand::fromCompany)
        logger.info { "Parsed ${companies.count()} unique brands." }
        val brandDao = repo.addBrands(brands).commit() as BrandDao

        val storeArticlesMap = mapStoreArticlesFromEntries(entries, brandDao)
        val articles = storeArticlesMap.map { it.value.first }.asSequence()
        logger.info { "Parsed ${storeArticlesMap.size} articles." }
        val articleDao = repo.addArticles(articles).commit() as ArticleDao
        val repoArticles = articleDao.list()

        // TODO This was written in a much too short time without any designing
        //      beforehand, this should really be reimplemented in the future
        // TODO Improve code duplication for store article filtering
        val filteredStoreArticleMap = storeArticlesMap
            .filterValues { (origArticle) ->
                repoArticles.contains(origArticle)
            }.mapValues { (_, articleAndPrice) ->
                val article = articleAndPrice.first
                val price = articleAndPrice.second

                val fetchedArticle = articleDao.findByProps(
                    article.brand,
                    article.name,
                    article.articleUnit,
                    article.quantity
                )

                requireNotNull(fetchedArticle) {
                    "Store article could not be fetched: $article"
                }

                Pair(fetchedArticle, price)
            }

        val storeArticles =
            filteredStoreArticleMap.map { (storeArticleId, articleAndPrice) ->
                val (article, _) = articleAndPrice

                StoreArticle(
                    store,
                    storeArticleId,
                    article,
                    timestamp
                )
            }
        logger.info { "Parsed ${storeArticles.size} store article mappings." }
        repo.addStoreArticles(storeArticles.asSequence()).commit()

        val storeArticlePrices =
            filteredStoreArticleMap.mapNotNull { (_, articleAndPrice) ->
                val (article, price) = articleAndPrice

                CurrentPrice(store, article, price, timestamp)
            }
        logger.info { "Parsed ${storeArticles.size} store article prices." }
        repo.addCurrentPrices(storeArticlePrices.asSequence()).commit()

    }

    private fun mapStoreArticlesFromEntries(
        entries: Sequence<T>,
        brandDao: BrandDao
    ) =
        entries.associate { entry ->
            val storeArticleId = parseInternalIdentifier(entry)
            val articleBrand =
                parseArticleBrand(entry, brandDao) ?: storeDefaultBrand
            val articleName = parseArticleName(entry, articleBrand)

            require(articleName.isNotBlank()) {
                val origArticleName = parseArticleFullName(entry)

                "Article name must not be empty. Original article name: $origArticleName"
            }

            val article = Article(
                brand = articleBrand,
                name = articleName,
                description = parseLongDescription(entry),
                originCountry = parseOriginCountry(entry),
                articleUnit = parseArticleUnit(entry),
                quantity = parseQuantity(entry),
                weightable = parseIsWeightable(entry)
            )

            storeArticleId to Pair(article, parseArticlePrice(entry))
        }

    private fun mapCompaniesFromEntries(entries: Sequence<T>) =
        entries.mapNotNull(::parseBrandName).distinctBy(String::lowercase)
            .map(Company::fromShortName)

    override fun parseEntry(file: File) {
        val fileName = file.name
        val fileExt = file.extension
        val fileModified = Date(file.lastModified()).toInstant()

        // TODO Should not rely solely on extension here
        val inputStream = when (fileExt) {
            "json" -> file.inputStream()
            "br" -> decodeBrotliFile(file)
            else -> throw IllegalArgumentException("Could not parse file '$fileName' with extension '$fileExt'.")
        }

        parseEntry(inputStream, fileModified)
    }

    override fun parseEntries(files: List<File>) {
        logger.info { "Parsing ${files.size} files..." }

        files.forEach(::parseEntry)

        logger.info { "Parsed ${files.size} files successfully." }
    }

    private fun decodeBrotliFile(brotliFile: File): ByteArrayInputStream {
        val fileName = brotliFile.name

        try {
            Brotli4jLoader.ensureAvailability()
        } catch (e: UnsatisfiedLinkError) {
            logger.error { "Could not decode brotli file '$fileName' because library could not be loaded: $e" }
        }

        logger.info { "Decompressing brotli file '$fileName'..." }

        val directDecompress: DirectDecompress =
            Decoder.decompress(brotliFile.inputStream().readAllBytes())

        when (directDecompress.resultStatus) {
            DecoderJNI.Status.DONE -> logger.info { "Successfully decompressed file '$fileName'." }
            else -> logger.error { "Some error occurred while decompressing file '$fileName': ${directDecompress.resultStatus.name}" }
        }

        return directDecompress.getDecompressedData().inputStream()
    }

    companion object {
        private val apostropheRegex = Regex("""[\u0060\u00B4\uFF07]+""")
        private val malformedStringRegex =
            Regex("""([\u00A0\u202F\u205F\u3000]+|\s{2,})""")

        fun escapeArticleString(string: String): String {
            return string
                .replace(apostropheRegex, "'")
                .replace(malformedStringRegex, " ")
                .trim()
                .encodeToByteArray()
                .decodeToString()
        }
    }
}
