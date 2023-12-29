package org.dakralex.pricevista.parser.hofer

import kotlinx.serialization.Serializable

@Serializable
class HoferJsonProductProviderItem(
    val available: Boolean,
    val depositFee: Double? = null,
    val displayUnit: String,
    val isBulk: Boolean,
    val isOffer: Boolean,
    val isOneProductForFree: Boolean,
    val maxWeightStep: Int,
    val minWeightStep: Double,
    val offerValidTo: String? = null,
    val originalPriceIfOffer: String? = null,
    val originalUnitPriceIfOffer: String? = null,
    val packageBaseUnit: String,
    val packageQuantity: Int,
    val price: Double,
    val product: String? = null,
    val productID: Int,
    val productProviderProviderDepositProducList: List<String>,
    val providerID: Int,
    val providerProductImageUrl: String,
    val providerProductName: String,
    val rokshDiscountPrice: String? = null,
    val secondaryUnit: String? = null,
    val secondaryUnitPrice: Int,
    val shopAvailable: String? = null,
    val unit: String? = null,
    val unitPrice: String,
    val unitPriceInt: Int
)