package org.dakralex.pricevista.parser.spar

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.QUANTITY_MATH_CONTEXT
import org.dakralex.pricevista.entities.MeasurementUnit
import org.dakralex.pricevista.entities.data.EMeasurementUnit
import org.dakralex.pricevista.parser.JsonParser
import java.io.InputStream
import java.math.BigDecimal

private val logger = KotlinLogging.logger {}

object SparJsonParser : JsonParser<SparJsonEntry>() {
    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJsonFromInputStream(inputStream: InputStream): List<SparJsonEntry> {
        return inputStream.use { Json.decodeFromStream(it) }
    }

    override fun parseInternalIdentifier(entry: SparJsonEntry): String {
        return entry.masterValues.productNumber
    }

    override fun parseBrandName(entry: SparJsonEntry): String {
        val ecrBrand = entry.masterValues.ecrBrand
        val brand = entry.masterValues.brand?.reversed()?.joinToString(" ")

        if (brand == null && ecrBrand == null) {
            logger.warn { "Brand and ECR brand for article '${entry.masterValues.name}' are null" }
        }

        return ecrBrand ?: brand ?: "Unknown"
    }

    override fun parseArticleFullName(entry: SparJsonEntry): String {
        return entry.masterValues.shortDescription ?: entry.masterValues.title
    }

    override fun parseLongDescription(entry: SparJsonEntry): String? {
        return entry.masterValues.description
    }

    override fun parseMeasurementUnit(entry: SparJsonEntry): MeasurementUnit {
        return EMeasurementUnit.PIECE.unit

//        val descriptor = entry.masterValues.shortDescription3?.lowercase()
//        val descriptors = descriptor
//            ?.replace("""/\d*/g""".toRegex(), "")
//            ?.trim()
//            ?.split(" ")
//            ?: listOf()
//
//        return descriptors.map { desc ->
//            return when (desc) {
//                "stk" -> EMeasurementUnit.PIECE.unit
//                else -> EMeasurementUnit.entries.first { it.name == desc }.unit
//            }
//        }.first<MeasurementUnit>()
    }

    override fun parseQuantity(entry: SparJsonEntry): BigDecimal {
        return BigDecimal(0.0, QUANTITY_MATH_CONTEXT)

//        val descriptor = entry.masterValues.shortDescription3?.lowercase()
//        val descriptors = descriptor
//            ?.replace("""/\w*/g""".toRegex(), "")
//            ?.replace(",", ".")
//            ?.trim()
//            ?.split(" ")
//            ?: listOf()
//
//        return descriptors.map { desc ->
//            BigDecimal(
//                desc,
//                QUANTITY_MATH_CONTEXT
//            )
//        }.first()
    }

    override fun parseIsWeightable(entry: SparJsonEntry): Boolean {
        return entry.masterValues.approxWeightProduct == "true" // TODO Is that enough?
    }

    override fun parseImageUrls(entry: SparJsonEntry): List<String> {
        return listOf(entry.masterValues.imageUrl)
    }
}
