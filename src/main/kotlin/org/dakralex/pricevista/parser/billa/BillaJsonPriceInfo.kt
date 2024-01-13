package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable
import org.dakralex.pricevista.parser.serializer.IntAsStringSerializer

@Serializable
data class BillaJsonPriceInfo(
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
    val crossed: BillaJsonPrice? = null,
    /**
     * Discount percentage as a negative number ranging from [null;-100]
     **/
    val discountPercentage: Int? = null,
    val regular: BillaJsonPriceCategory
)
