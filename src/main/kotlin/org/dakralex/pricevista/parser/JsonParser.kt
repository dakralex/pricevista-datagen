package org.dakralex.pricevista.parser

import com.aayushatharva.brotli4j.Brotli4jLoader
import com.aayushatharva.brotli4j.decoder.Decoder
import com.aayushatharva.brotli4j.decoder.DecoderJNI
import com.aayushatharva.brotli4j.decoder.DirectDecompress
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.dakralex.pricevista.entities.*
import org.dakralex.pricevista.parser.billa.BillaJsonEntry
import org.dakralex.pricevista.parser.hofer.HoferJsonEntry
import org.dakralex.pricevista.parser.spar.SparJsonEntry
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import kotlin.math.min


private val logger = KotlinLogging.logger {}

val module = SerializersModule {
    polymorphic(JsonEntry::class) {
        subclass(BillaJsonEntry.serializer())
        subclass(HoferJsonEntry.serializer())
        subclass(SparJsonEntry.serializer())
    }
}

abstract class JsonParser<T : JsonEntry> {
    private val json = Json { serializersModule = module }

    private var parsedArticles: List<Article> = mutableListOf()
    private var parsedArticleVariants: List<ArticleVariant> = mutableListOf()
    private var parsedBrands: List<Brand> = mutableListOf()
    private var parsedCategories: List<Category> = mutableListOf()
    private var parsedCompanies: List<Company> = mutableListOf()

    abstract fun decodeJsonFromInputStream(inputStream: InputStream): List<T>

    abstract fun parseArticleFullName(entry: T): String
    abstract fun parseBrandName(entry: T): String
    abstract fun parseDescription(entry: T): String?
    abstract fun parseImageUrl(entry: T): String?

    private fun parseArticleBrand(
        entry: T,
        brands: List<Brand> = parsedBrands
    ): Brand? {
        return brands.find { parseBrandName(entry) == it.name }
            ?: brands.find { parseArticleFullName(entry).contains(it.name) }
    }

    private fun parseArticleName(entry: T, brand: Brand): String {
        return parseArticleFullName(entry).replace(brand.name, "", true).trim()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun parseEntries(inputStream: InputStream) {
        // TODO This should be doable with abstraction, but I got an unresolvable
        //      exception I couldn't fix, see kotlinx.serialization#2537
        val entries = decodeJsonFromInputStream(inputStream)
        logger.info { "Parsed ${entries.size} entries." }

        val brands =
            entries.map { parseBrandName(it) }.distinctBy { it.uppercase() }
        // TODO Find out in which country these companies operate
        parsedCompanies += brands.map { Company(name = it, country = "AUT") }
        // TODO Remove product line from data structure
        parsedBrands += parsedCompanies.map { Brand(it, "Unspecified") }

        val parsedBrandsSize = parsedBrands.size
        if (logger.isDebugEnabled()) {
            val sampleSize = min(parsedBrandsSize, 9)
            val sample = brands.slice(0..sampleSize)
            logger.debug { "Sample: $sample" }
        }
        logger.info { "Parsed $parsedBrandsSize unique brands." }

        parsedArticles += entries.map { entry ->
            val articleBrand = parseArticleBrand(entry)
                ?: Brand(company = Company(name = "Unknown", country = "AUT"))

            Article(
                name = parseArticleName(entry, articleBrand),
                brand = articleBrand,
                description = parseDescription(entry),
                imageUrl = parseImageUrl(entry)
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
