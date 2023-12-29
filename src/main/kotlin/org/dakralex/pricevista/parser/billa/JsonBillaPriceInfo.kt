package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable
import org.dakralex.pricevista.parser.IntAsStringSerializer

/**
 * This value class represents a price value in a JSON export, where the prices
 * are stored as whole integers to the smallest money unit (e.g. Euro cents).
 */
@JvmInline
@Serializable
value class JsonBillaPrice(val value: Int)

@Serializable
data class JsonBillaPriceCategory(
    val perStandardizedQuantity: JsonBillaPrice,
    val promotionQuantity: Int? = null,
    val promotionText: String? = null,
    val promotionType: JsonBillaPromotionType? = null,
    val promotionValue: JsonBillaPrice? = null,
    val promotionValuePerStandardizedQuantity: Int? = null,
    val tags: List<String>, // TODO Use enum class JsonBillaPriceTag in the future
    val value: JsonBillaPrice
)

@Serializable
data class JsonBillaPriceInfo(
    /**
     * TODO Unsure about what the basePriceFactor is (value is either null, 1, 100, or 500)
     **/
    @Serializable(with = IntAsStringSerializer::class)
    val basePriceFactor: Int? = null,
    /**
     * Localized measurement unit suffix (e.g. "Kilogramm", "Stück", "Bund", etc.)
     */
    val baseUnitLong: String,
    /**
     * Localized abbreviated measurement unit suffix (e.g. "kg", "Stk", "Bund", etc.)
     */
    val baseUnitShort: String,
    /**
     * Displayed, crossed-out whole-number price (for discounts)
     **/
    val crossed: JsonBillaPrice? = null,
    /**
     * Discount percentage as a negative number ranging from [null;-100]
     **/
    val discountPercentage: Int? = null,
    val regular: JsonBillaPriceCategory
)
