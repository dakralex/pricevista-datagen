package org.dakralex.pricevista.parser.hofer

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.parser.JsonParser
import java.io.InputStream

private val logger = KotlinLogging.logger {}

object HoferJsonParser : JsonParser<HoferJsonEntry>() {
    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): List<HoferJsonEntry> {
        return inputStream.use { Json.decodeFromStream(it) }
    }

    override fun parseArticleFullName(entry: HoferJsonEntry): String {
        return entry.productName
    }

    override fun parseBrandName(entry: HoferJsonEntry): String {
        // TODO See if providerDetails has any more info on this
        val brand = entry.brand

        if (brand.isNullOrBlank()) {
            logger.warn { "Brand field for article '${entry.productName}' is null" }
        }

        return brand ?: "Unknown"
    }

    override fun parseDescription(entry: HoferJsonEntry): String {
        return entry.description
    }

    override fun parseImageUrl(entry: HoferJsonEntry): String {
        return entry.mediaUrlLarge
    }
}
