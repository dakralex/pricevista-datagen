package org.dakralex.pricevista.parser.hofer

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeToSequence
import org.dakralex.pricevista.PRICE_MATH_CONTEXT
import org.dakralex.pricevista.database.PriceVistaDatabase
import org.dakralex.pricevista.entities.ArticleUnit
import org.dakralex.pricevista.entities.data.EStore
import org.dakralex.pricevista.parser.StoreJsonParser
import org.dakralex.pricevista.parser.guessArticleUnit
import java.io.InputStream
import java.math.BigDecimal

private val logger = KotlinLogging.logger {}

class HoferJsonParser(
    repo: PriceVistaDatabase
) : StoreJsonParser<HoferJsonEntry>(repo) {
    override val store = EStore.HOFER.store

    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): Sequence<HoferJsonEntry> {
        return inputStream.use { Json.decodeToSequence(it) }
    }

    override fun parseInternalIdentifier(entry: HoferJsonEntry): String {
        return entry.productId.toString()
    }

    override fun parseBrandName(entry: HoferJsonEntry): String? {
        // TODO See if providerDetails has any more info on this
        val brand = entry.brand

        if (brand.isNullOrBlank()) {
            logger.debug { "Brand field for article '${entry.productName}' is null" }
        }

        return brand
    }

    override fun parseArticleFullName(entry: HoferJsonEntry): String {
        return escapeArticleString(entry.productName)
    }

    override fun parseLongDescription(entry: HoferJsonEntry): String? {
        // TODO The descriptions must be escaped, please reimplement later
        return null
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

    override fun parseArticlePrice(entry: HoferJsonEntry): Long {
        // TODO Do retrieve the number of decimals from store currency
        return BigDecimal(entry.price, PRICE_MATH_CONTEXT).scaleByPowerOfTen(2)
            .toLong()
    }
}
