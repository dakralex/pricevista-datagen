package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable

@Serializable
data class BillaJsonPriceCategory(
    val perStandardizedQuantity: BillaJsonPrice,
    val promotionQuantity: Int? = null,
    val promotionText: String? = null,
    val promotionType: BillaJsonPromotionType? = null,
    val promotionValue: BillaJsonPrice? = null,
    val promotionValuePerStandardizedQuantity: Int? = null,
    val tags: List<String>, // TODO Use enum class JsonBillaPriceTag in the future
    val value: BillaJsonPrice
)
