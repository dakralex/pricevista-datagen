package org.dakralex.pricevista.parser.billa

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeToSequence
import org.dakralex.pricevista.database.PriceVistaDatabase
import org.dakralex.pricevista.entities.ArticleUnit
import org.dakralex.pricevista.entities.data.EStore
import org.dakralex.pricevista.parser.StoreJsonParser
import org.dakralex.pricevista.parser.guessArticleUnit
import java.io.InputStream

private val logger = KotlinLogging.logger {}

class BillaJsonParser(
    repo: PriceVistaDatabase
) : StoreJsonParser<BillaJsonEntry>(repo) {
    override val store = EStore.BILLA.store

    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): Sequence<BillaJsonEntry> {
        return inputStream.use { Json.decodeToSequence(it) }
    }

    override fun parseInternalIdentifier(entry: BillaJsonEntry): String {
        return entry.productId
    }

    override fun parseBrandName(entry: BillaJsonEntry): String? {
        val brand = entry.brand?.name

        if (brand.isNullOrBlank()) {
            logger.debug { "Brand field for article '${entry.name}' is null" }
        }

        return brand
    }

    override fun parseArticleFullName(entry: BillaJsonEntry): String {
        return escapeArticleString(entry.name)
    }

    override fun parseLongDescription(entry: BillaJsonEntry): String? {
        // TODO The descriptions must be escaped, please reimplement later
        return null
    }

    override fun parseArticleUnit(entry: BillaJsonEntry): ArticleUnit {
        val longName = entry.volumeLabelLong
        val shortName = entry.volumeLabelShort ?: entry.volumeLabelKey

        return guessArticleUnit(longName, shortName)
            ?: super.parseArticleUnit(entry)
    }

    override fun parseQuantity(entry: BillaJsonEntry): Double {
        return entry.amount
    }

    override fun parseIsWeightable(entry: BillaJsonEntry): Boolean {
        return entry.weightArticle
    }

    override fun parseImageUrls(entry: BillaJsonEntry): List<String> {
        return entry.images
    }

    override fun parseArticlePrice(entry: BillaJsonEntry): Long {
        return entry.price.regular.value
    }
}
