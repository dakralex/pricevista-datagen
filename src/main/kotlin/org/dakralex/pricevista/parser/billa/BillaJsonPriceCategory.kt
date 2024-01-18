package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable

/**
 * This type represents a price value in a JSON export, where the prices are
 * stored as whole integers to the smallest money unit (e.g. Euro cents).
 */
typealias BillaJsonPrice = Long

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
