package org.dakralex.pricevista.parser

import com.aayushatharva.brotli4j.Brotli4jLoader
import com.aayushatharva.brotli4j.decoder.Decoder
import com.aayushatharva.brotli4j.decoder.DecoderJNI
import com.aayushatharva.brotli4j.decoder.DirectDecompress
import io.github.oshai.kotlinlogging.KotlinLogging
import org.dakralex.pricevista.QUANTITY_MATH_CONTEXT
import org.dakralex.pricevista.entities.*
import org.dakralex.pricevista.entities.data.EMeasurementUnit
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.math.BigDecimal


private val logger = KotlinLogging.logger {}

abstract class JsonParser<T : JsonEntry> {
    private var parsedCompanies: List<Company> = mutableListOf()
    private var parsedBrands: List<Brand> = mutableListOf()
    private var parsedCategories: List<Category> = mutableListOf()
    private var parsedArticles: List<Article> = mutableListOf()
    private var parsedArticleCategories: List<ArticleCategory> = mutableListOf()
    private var parsedArticleImages: List<ArticleImage> = mutableListOf()
    private var parsedStoreArticles: List<StoreArticleMap> = mutableListOf()
    private var parsedStoreCategories: List<StoreCategoryMap> = mutableListOf()


    abstract fun decodeJsonFromInputStream(inputStream: InputStream): List<T>


    abstract fun parseInternalIdentifier(entry: T): String

    abstract fun parseBrandName(entry: T): String?

    abstract fun parseArticleFullName(entry: T): String

    abstract fun parseLongDescription(entry: T): String?

    open fun parseOriginCountry(entry: T): Country? = null

    open fun parseMeasurementUnit(entry: T): MeasurementUnit =
        EMeasurementUnit.MISC_PIECE.unit

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

    private fun parseEntries(inputStream: InputStream) {
        // TODO This should be doable with abstraction, but I got an unresolvable
        //      exception I couldn't fix, see kotlinx.serialization#2537
        val entries = decodeJsonFromInputStream(inputStream)
        logger.info { "Parsed ${entries.size} entries." }

        val brands = entries.mapNotNull { parseBrandName(it) }
            .distinctBy(String::lowercase)
        parsedCompanies += brands.map { Company(shortName = it) }

        parsedBrands += parsedCompanies.map { Brand(it) }
        logger.info { "Parsed ${parsedBrands.size} unique brands." }

        parsedArticles += entries.map { entry ->
            // TODO Find store's default brand or something similar
            val articleBrand = parseArticleBrand(entry)

            Article(
                brand = articleBrand,
                name = parseArticleName(entry, articleBrand),
                description = parseLongDescription(entry),
                originCountry = parseOriginCountry(entry),
                unit = parseMeasurementUnit(entry),
                quantity = parseQuantity(entry),
                weightable = parseIsWeightable(entry)
            )
        }
    }

    fun parseEntries(files: List<File>) {
        logger.info { "Parsing ${files.size} JSON files..." }

        val inputStreams = files.map {
            when (it.extension) {
                "json" -> it.inputStream()
                "br" -> decodeBrotliFile(it)
                else -> TODO("Not implemented")
            }
        }

        inputStreams.map { parseEntries(it) }
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
