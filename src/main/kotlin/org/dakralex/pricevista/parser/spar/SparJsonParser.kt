package org.dakralex.pricevista.parser.spar

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

class SparJsonParser(
    repo: PriceVistaDatabase
) : StoreJsonParser<SparJsonEntry>(repo) {
    override val store = EStore.SPAR.store

    private val decimalSepRegex = Regex(""",""")
    private val unitRegex = Regex("""[\sa-z]+""")
    private val quantityRegex = Regex("""[,.\d]""")
    private val depositTypeRegex = Regex("""(einweg|mehrweg)""")

    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): Sequence<SparJsonEntry> {
        return inputStream.use { Json.decodeToSequence(it) }
    }

    override fun parseInternalIdentifier(entry: SparJsonEntry): String {
        return entry.masterValues.productNumber
    }

    override fun parseBrandName(entry: SparJsonEntry): String? {
        val ecrBrand = entry.masterValues.ecrBrand
        val brand = entry.masterValues.brand?.reversed()?.joinToString(" ")

        // TODO If this happens, search in the product name a value of the brand repository
        if (brand == null && ecrBrand == null) {
            val articleName = parseArticleFullName(entry)
            val articleId = parseInternalIdentifier(entry)

            logger.debug {
                "Brand and ECR brand  are null " +
                        "for article '$articleName' ($articleId)"
            }
        }

        return ecrBrand ?: brand
    }

    override fun parseArticleFullName(entry: SparJsonEntry): String {
        if (entry.masterValues.shortDescription2 == null ||
            entry.masterValues.shortDescription == entry.masterValues.shortDescription3
        ) {
            return escapeArticleString(entry.masterValues.title)
        }

        val articleFullName =
            entry.masterValues.shortDescription ?: entry.masterValues.title

        return escapeArticleString(articleFullName)
    }

    override fun parseLongDescription(entry: SparJsonEntry): String? {
        return if (entry.masterValues.description != null) {
            escapeArticleString(entry.masterValues.description)
        } else null
    }

    override fun parseArticleUnit(entry: SparJsonEntry): ArticleUnit {
        val shortDesc3 = entry.masterValues.shortDescription3?.lowercase()

        val unitDescriptor = shortDesc3
            ?.replace(quantityRegex, "")
            ?.replace(depositTypeRegex, "")
            ?.trim()

        // TODO Implement second trial with pricePerUnit property
        //      and /[\,\.\d\€\/]+/g as the removal regex

        return guessArticleUnit(shortName = unitDescriptor)
            ?: super.parseArticleUnit(entry)
    }

    override fun parseQuantity(entry: SparJsonEntry): Double {
        val shortDesc3 = entry.masterValues.shortDescription3?.lowercase()

        val quantityDescriptor = shortDesc3?.replace(unitRegex, "")
            ?.replace(decimalSepRegex, ".")?.trim()

        try {
            return quantityDescriptor?.toDouble() ?: super.parseQuantity(entry)
        } catch (e: RuntimeException) {
            val articleName = parseArticleFullName(entry)
            val articleId = parseInternalIdentifier(entry)

            logger.debug {
                "Could not parse quantity with " +
                        "shortDesc3 = '$shortDesc3' and " +
                        "quantityDescriptor = '$quantityDescriptor' " +
                        "for article '$articleName' ($articleId).\n$e"
            }
            return super.parseQuantity(entry)
        }
    }

    override fun parseIsWeightable(entry: SparJsonEntry): Boolean {
        return entry.masterValues.approxWeightProduct == "true" // TODO Is that enough?
    }

    override fun parseImageUrls(entry: SparJsonEntry): List<String> {
        return listOf(entry.masterValues.imageUrl)
    }

    override fun parseArticlePrice(entry: SparJsonEntry): Long {
        // TODO Do retrieve the number of decimals from store currency
        return BigDecimal(
            entry.masterValues.price,
            PRICE_MATH_CONTEXT
        ).scaleByPowerOfTen(2).toLong()
    }
}
