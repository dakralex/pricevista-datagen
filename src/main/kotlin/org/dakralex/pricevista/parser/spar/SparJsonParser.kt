package org.dakralex.pricevista.parser.spar

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.QUANTITY_MATH_CONTEXT
import org.dakralex.pricevista.entities.ArticleUnit
import org.dakralex.pricevista.entities.data.EStore
import org.dakralex.pricevista.entities.dictionary.guessArticleUnit
import org.dakralex.pricevista.parser.JsonParser
import java.io.InputStream
import java.math.BigDecimal

private val logger = KotlinLogging.logger {}

object SparJsonParser : JsonParser<SparJsonEntry>() {
    override val store = EStore.SPAR.store

    private val decimalSepRegex = Regex(""",""")
    private val unitRegex = Regex("""[\sa-z]+""")
    private val quantityRegex = Regex("""[,.\d]""")
    private val depositTypeRegex = Regex("""(einweg|mehrweg)""")

    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): List<SparJsonEntry> {
        return inputStream.use { Json.decodeFromStream(it) }
    }

    override fun parseInternalIdentifier(entry: SparJsonEntry): String {
        return entry.masterValues.productNumber
    }

    override fun parseBrandName(entry: SparJsonEntry): String? {
        val ecrBrand = entry.masterValues.ecrBrand
        val brand = entry.masterValues.brand?.reversed()?.joinToString(" ")

        // TODO If this happens, search in the product name a value of the brand repository
        if (brand == null && ecrBrand == null) {
            logger.warn {
                "Brand and ECR brand for article '${entry.masterValues.name}' (${
                    parseInternalIdentifier(
                        entry
                    )
                }) are null"
            }
        }

        return ecrBrand ?: brand
    }

    override fun parseArticleFullName(entry: SparJsonEntry): String {
        if (entry.masterValues.shortDescription2 == null ||
            entry.masterValues.shortDescription == entry.masterValues.shortDescription3
        ) {
            return entry.masterValues.title
        }

        return entry.masterValues.shortDescription ?: entry.masterValues.title
    }

    override fun parseLongDescription(entry: SparJsonEntry): String? {
        return entry.masterValues.description
    }

    override fun parseArticleUnit(entry: SparJsonEntry): ArticleUnit {
        val shortDesc3 = entry.masterValues.shortDescription3?.lowercase()

        val unitDescriptor = shortDesc3?.replace(quantityRegex, "")
            ?.replace(depositTypeRegex, "")?.trim()

        // TODO Implement second trial with pricePerUnit property
        //      and /[\,\.\d\€\/]+/g as the removal regex

        return guessArticleUnit(shortName = unitDescriptor)
            ?: super.parseArticleUnit(entry)
    }

    override fun parseQuantity(entry: SparJsonEntry): BigDecimal {
        val shortDesc3 = entry.masterValues.shortDescription3?.lowercase()

        val quantityDescriptor = shortDesc3?.replace(unitRegex, "")
            ?.replace(decimalSepRegex, ".")?.trim()

        try {
            return BigDecimal(quantityDescriptor, QUANTITY_MATH_CONTEXT)
        } catch (e: RuntimeException) {
            logger.warn {
                "Could not parse quantity with shortDesc3 = '$shortDesc3' " +
                        "and quantityDescriptor = '$quantityDescriptor' for article " +
                        "'${entry.masterValues.name}' (${
                            parseInternalIdentifier(
                                entry
                            )
                        }).\n$e"
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
}
