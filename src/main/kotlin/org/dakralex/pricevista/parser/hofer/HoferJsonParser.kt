package org.dakralex.pricevista.parser.hofer

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.entities.ArticleUnit
import org.dakralex.pricevista.entities.data.EStore
import org.dakralex.pricevista.entities.dictionary.guessArticleUnit
import org.dakralex.pricevista.parser.JsonParser
import java.io.InputStream

private val logger = KotlinLogging.logger {}

object HoferJsonParser : JsonParser<HoferJsonEntry>() {
    override val store = EStore.HOFER.store

    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): List<HoferJsonEntry> {
        return inputStream.use { Json.decodeFromStream(it) }
    }

    override fun parseInternalIdentifier(entry: HoferJsonEntry): String {
        return entry.productId.toString()
    }

    override fun parseBrandName(entry: HoferJsonEntry): String? {
        // TODO See if providerDetails has any more info on this
        val brand = entry.brand

        if (brand.isNullOrBlank()) {
            logger.warn { "Brand field for article '${entry.productName}' is null" }
        }

        return brand
    }

    override fun parseArticleFullName(entry: HoferJsonEntry): String {
        return entry.productName
    }

    override fun parseLongDescription(entry: HoferJsonEntry): String {
        return entry.description
    }

    override fun parseArticleUnit(entry: HoferJsonEntry): ArticleUnit {
        // TODO Uhm... I think we shouldn't collect the display unit but the
        //      priceUnitType here so we know in what unit the item is weighted
        return guessArticleUnit(entry.displayUnit)
            ?: super.parseArticleUnit(entry)
    }

    override fun parseIsWeightable(entry: HoferJsonEntry): Boolean {
        return entry.isBulk // TODO Is that enough?
    }

    override fun parseImageUrls(entry: HoferJsonEntry): List<String> {
        return listOf(entry.mediaUrlLarge)
    }
}
