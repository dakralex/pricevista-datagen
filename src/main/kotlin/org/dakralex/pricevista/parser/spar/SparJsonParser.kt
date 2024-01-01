package org.dakralex.pricevista.parser.spar

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.parser.JsonParser
import java.io.InputStream

private val logger = KotlinLogging.logger {}

object SparJsonParser : JsonParser<SparJsonEntry>() {
    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): List<SparJsonEntry> {
        return inputStream.use { Json.decodeFromStream(it) }
    }

    override fun parseArticleFullName(entry: SparJsonEntry): String {
        return entry.masterValues.shortDescription ?: entry.masterValues.title
    }

    override fun parseBrandName(entry: SparJsonEntry): String {
        val ecrBrand = entry.masterValues.ecrBrand
        val brand = entry.masterValues.brand?.reversed()?.joinToString { " " }

        if (brand == null && ecrBrand == null) {
            logger.warn { "Brand and ECR brand for article '${entry.masterValues.name}' are null" }
        }

        if (brand != ecrBrand) {
            logger.warn { "Brand and ECR brand for article '${entry.masterValues.name}' are not equal: '$brand' != '$ecrBrand'" }
        }

        return ecrBrand ?: brand ?: "Unknown"
    }

    override fun parseDescription(entry: SparJsonEntry): String? {
        return entry.masterValues.description
    }

    override fun parseImageUrl(entry: SparJsonEntry): String {
        return entry.masterValues.imageUrl
    }
}
