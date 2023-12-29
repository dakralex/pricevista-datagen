package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File
import java.util.*

class IPriceTypeDefs(
    val name: String, // TODO Name belongs to enum class "PriceTypes"
    val startDate: Date? = null,
    val startDateText: String? = null
)

data class IPriceAdditionalInfo(
    val vptext: String? = null
)

class IPrice(
    val normal: Double,
    val sale: Double,
    val final: Double,
    val unit: String,
    val isQuantityDiscount: Boolean,
    val isPlusPromotion: Boolean,
    val priceTypeDefinitions: IPriceTypeDefs,
    val defaultPriceTypes: Array<String>, // TODO Entries belong to enum class "PriceTypes"
    val bulkDiscountPriceTypes: Array<String>, // TODO Entries belong to enum class "PriceTypes"
    val priceAdditionalInfo: IPriceAdditionalInfo,
)

class ITitle(
    val articleId: String,
    val name: String,
    val brand: String? = null,
    val canonicalPath: String,
    val articleGroupIds: String,
    val price: IPrice,
    val vatCode: String, // TODO Enum class VAT_CODE (10, 13, 20)
    val loyaltyPrice: IPrice,
    val grammage: String,
    val grammageUnit: String,
    val grammageBadge: String,
    val grammagePriceFactor: Double? = null,
    val isWeightArticle: Boolean? = false,
    val quantitySteps: Array<Number>,
    val description: String,
    val hasDescription: Boolean,

    )

@OptIn(ExperimentalSerializationApi::class)
fun parseBillaImport(file: File) {
    file.inputStream().use {
        Json.decodeFromStream<Array<ITitle>>(it)
    }
}