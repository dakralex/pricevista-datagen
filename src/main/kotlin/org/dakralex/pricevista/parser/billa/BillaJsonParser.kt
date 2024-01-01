package org.dakralex.pricevista.parser.billa

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.parser.JsonParser
import java.io.InputStream

private val logger = KotlinLogging.logger {}

object BillaJsonParser : JsonParser<BillaJsonEntry>() {
    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): List<BillaJsonEntry> {
        return inputStream.use { Json.decodeFromStream(it) }
    }

    override fun parseArticleFullName(entry: BillaJsonEntry): String {
        return entry.name
    }

    override fun parseBrandName(entry: BillaJsonEntry): String {
        val brand = entry.brand?.name

        if (brand.isNullOrBlank()) {
            logger.warn { "Brand field for article '${entry.name}' is null" }
        }

        return brand ?: "Unknown"
    }

    override fun parseDescription(entry: BillaJsonEntry): String? {
        return entry.descriptionLong
            ?: entry.descriptionShort
    }

    override fun parseImageUrl(entry: BillaJsonEntry): String? {
        return entry.images.firstOrNull()
    }
}
