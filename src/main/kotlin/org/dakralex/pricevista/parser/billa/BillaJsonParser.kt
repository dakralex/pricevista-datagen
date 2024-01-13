package org.dakralex.pricevista.parser.billa

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.QUANTITY_MATH_CONTEXT
import org.dakralex.pricevista.entities.ArticleUnit
import org.dakralex.pricevista.entities.data.EStore
import org.dakralex.pricevista.parser.StoreJsonParser
import org.dakralex.pricevista.parser.guessArticleUnit
import java.io.InputStream
import java.math.BigDecimal

private val logger = KotlinLogging.logger {}

object BillaJsonParser : StoreJsonParser<BillaJsonEntry>() {
    override val store = EStore.BILLA.store

    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): List<BillaJsonEntry> {
        return inputStream.use { Json.decodeFromStream(it) }
    }

    override fun parseInternalIdentifier(entry: BillaJsonEntry): String {
        return entry.productId
    }

    override fun parseBrandName(entry: BillaJsonEntry): String? {
        val brand = entry.brand?.name

        if (brand.isNullOrBlank()) {
            logger.warn { "Brand field for article '${entry.name}' is null" }
        }

        return brand
    }

    override fun parseArticleFullName(entry: BillaJsonEntry): String {
        return entry.name
    }

    override fun parseLongDescription(entry: BillaJsonEntry): String? {
        return entry.descriptionLong
            ?: entry.descriptionShort
    }

    override fun parseArticleUnit(entry: BillaJsonEntry): ArticleUnit {
        val longName = entry.volumeLabelLong
        val shortName = entry.volumeLabelShort ?: entry.volumeLabelKey

        return guessArticleUnit(longName, shortName)
            ?: super.parseArticleUnit(entry)
    }

    override fun parseQuantity(entry: BillaJsonEntry): BigDecimal {
        return BigDecimal(entry.amount, QUANTITY_MATH_CONTEXT)
    }

    override fun parseIsWeightable(entry: BillaJsonEntry): Boolean {
        return entry.weightArticle
    }

    override fun parseImageUrls(entry: BillaJsonEntry): List<String> {
        return entry.images
    }
}
