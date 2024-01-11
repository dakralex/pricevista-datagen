package org.dakralex.pricevista.parser

import com.aayushatharva.brotli4j.Brotli4jLoader
import com.aayushatharva.brotli4j.decoder.Decoder
import com.aayushatharva.brotli4j.decoder.DecoderJNI
import com.aayushatharva.brotli4j.decoder.DirectDecompress
import io.github.oshai.kotlinlogging.KotlinLogging
import org.dakralex.pricevista.QUANTITY_MATH_CONTEXT
import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.entities.*
import org.dakralex.pricevista.entities.data.EArticleUnit
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.math.BigDecimal
import java.util.*


private val logger = KotlinLogging.logger {}

abstract class JsonParser<T : JsonEntry> {
    abstract val store: Store

    open val storeDefaultBrand: Brand? = null

    private var parsedCompanies: List<Company> = mutableListOf()
    private var parsedBrands: List<Brand> = mutableListOf()
    private var parsedCategories: List<Category> =
        mutableListOf() // TODO Could add categories
    private var parsedArticles: List<Article> = mutableListOf()
    private var parsedArticleCategories: List<ArticleCategory> =
        mutableListOf() // TODO Could add categories
    private var parsedArticleImages: List<ArticleImage> = mutableListOf()
    private var parsedStoreArticles: List<StoreArticle> = mutableListOf()
    private var parsedStoreCategories: List<StoreCategory> =
        mutableListOf() // TODO Could add categories


    abstract fun decodeJsonFromInputStream(inputStream: InputStream): List<T>


    abstract fun parseInternalIdentifier(entry: T): String

    abstract fun parseBrandName(entry: T): String?

    abstract fun parseArticleFullName(entry: T): String

    abstract fun parseLongDescription(entry: T): String?

    open fun parseOriginCountry(entry: T): Country? = null

    open fun parseArticleUnit(entry: T): ArticleUnit =
        EArticleUnit.MISC_PIECE.unit

    open fun parseQuantity(entry: T): BigDecimal =
        BigDecimal(1.0, QUANTITY_MATH_CONTEXT)

    open fun parseIsWeightable(entry: T): Boolean = false

    open fun parseImageUrls(entry: T): List<String> = listOf()


    private fun parseArticleBrand(
        entry: T,
        brands: List<Brand> = parsedBrands
    ): Brand? {
        return brands.find { parseBrandName(entry) == it.company.shortName }
            ?: brands.find { parseArticleFullName(entry).contains(it.company.shortName) }
    }

    private fun parseArticleName(entry: T, brand: Brand?): String {
        return parseArticleFullName(entry)
            .replace(brand?.company?.shortName ?: "", "", true).trim()
    }

    private fun parseEntries(inputStream: InputStream, date: Date = Date()) {
        // TODO This should be doable with abstraction, but I got an unresolvable
        //      exception I couldn't fix, see kotlinx.serialization#2537
        val entries = decodeJsonFromInputStream(inputStream)
        logger.info { "Parsed ${entries.size} entries." }

        val brands = entries.mapNotNull { entry -> parseBrandName(entry) }
            .distinctBy(String::lowercase)
        parsedCompanies += brands.map { name -> Company(shortName = name) }

        parsedBrands += parsedCompanies.map { company -> Brand(company) }
        logger.info { "Parsed ${parsedBrands.size} unique brands." }

        parsedArticles += entries.map { entry ->
            val articleBrand = parseArticleBrand(entry) ?: storeDefaultBrand

            val article = Article(
                brand = articleBrand,
                name = parseArticleName(entry, articleBrand),
                description = parseLongDescription(entry),
                originCountry = parseOriginCountry(entry),
                unit = parseArticleUnit(entry),
                quantity = parseQuantity(entry),
                weightable = parseIsWeightable(entry)
            )

            val parsedImageUrls = parseImageUrls(entry)
            parsedArticleImages += parsedImageUrls.map { url ->
                ArticleImage(article, null, url)
            }

            parsedStoreArticles += StoreArticle(
                store,
                parseInternalIdentifier(entry),
                article,
                date
            )

            article
        }.distinctBy { article ->
            val brandName = article.brand?.company?.shortName
            val articleName = article.name

            "$brandName$articleName".lowercase()
        }
        logger.info { "Parsed ${parsedArticles.size} unique articles." }

        parsedArticleImages = parsedArticleImages.distinctBy {
            it.article.hashCode().toString() + it.imageUrl
        }
        logger.info { "Parsed ${parsedArticleImages.size} unique article images." }

        parsedStoreArticles =
            parsedStoreArticles.distinctBy { it.storeArticleId }
        logger.info { "Parsed ${parsedStoreArticles.size} unique store articles." }
    }

    fun parseEntries(files: List<File>) {
        logger.info { "Parsing ${files.size} JSON files..." }

        val inputStreamsTimestamped = files.map { file ->
            val date = Date(file.lastModified())

            val inputStream = when (file.extension) {
                "json" -> file.inputStream()
                "br" -> decodeBrotliFile(file)
                else -> TODO("Not implemented")
            }

            Pair(inputStream, date)
        }

        inputStreamsTimestamped.map { inputStream ->
            parseEntries(inputStream.first, inputStream.second)
        }
    }

    fun commit(db: Database) {
        logger.info { " " }
        logger.info { " " }
        logger.info { " " }
        logger.info { " " }
        logger.info { " " }

        logger.info { "Commit parsed companies..." }
        logger.debug { parsedCompanies }
        Company.insertBatch(db, parsedCompanies)

        logger.info { "Commit parsed brands..." }
        Brand.insertBatch(db, parsedBrands)

        logger.info { "Commit parsed articles..." }
        Article.insertBatch(db, parsedArticles)

        logger.info { "Commit parsed article images..." }
        ArticleImage.insertBatch(db, parsedArticleImages)

        logger.info { "Commit parsed store articles..." }
        StoreArticle.insertBatch(db, parsedStoreArticles)
    }

    private fun decodeBrotliFile(it: File): ByteArrayInputStream {
        Brotli4jLoader.ensureAvailability()

        logger.info { "Decompressing ${it.name}..." }

        val directDecompress: DirectDecompress =
            Decoder.decompress(it.inputStream().readAllBytes())

        when (directDecompress.resultStatus) {
            DecoderJNI.Status.DONE -> logger.info { "Successfully decompressed ${it.name}." }
            else -> logger.error { "Some error occurred while decompressing ${it.name}." }
        }

        return directDecompress.getDecompressedData().inputStream()
    }
}
