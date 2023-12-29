package org.dakralex.pricevista.parser.hofer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoferJsonEntry(
    val available: Boolean,
    @SerialName("basketItemID")
    val basketItemId: String? = null,
    val brand: String? = null,
    val category: HoferJsonCategory,
    @SerialName("categoryID")
    val categoryId: Int,
    @SerialName("categorySEOName")
    val categorySeoName: String,
    val depositFee: Double? = null,
    val description: String,
    val displayUnit: String,
    val galleryImageUrlList: String? = null,
    val icons: List<HoferJsonIcon>,
    val isBulk: Boolean,
    val isDiscountPD: String? = null,
    val isDiscountPPDD: String? = null,
    val isOffer: Boolean,
    val isOneProductForFree: Boolean,
    val isProductRokshDiscounted: Boolean,
    val manufacturer: String? = null,
    val maxWeightStep: Int,
    @SerialName("mediaUrlL")
    val mediaUrlLarge: String,
    @SerialName("mediaUrlM")
    val mediaUrlMedium: String,
    @SerialName("mediaUrlS")
    val mediaUrlSmall: String,
    val minPrice: Double,
    val minUnitPrice: Double,
    val minWeightStep: Double,
    val offerValidTo: String,
    val originalPriceIfOffer: String? = null,
    val originalUnitPriceIfOffer: String? = null,
    val price: Double,
    val priceUnitType: String? = null,
    val productDetails: HoferJsonProductDetails,
    @SerialName("productID")
    val productId: Int,
    val productMediaList: List<HoferJsonProductMediaListItem>,
    val productName: String,
    val productProvider: List<HoferJsonProductProviderItem>,
    @SerialName("productProviderID")
    val productProviderId: Int,
    val providerAvailable: Boolean,
    val providerDepositProductDtoList: List<String>,
    val providerOrderNumber: Int,
    val rokshDiscountBasketValue: String? = null,
    val rokshDiscountLevel: String? = null,
    val rokshDiscountPrice: Double? = null,
    @SerialName("sEOMetaDescripton")
    val seoMetaDescription: String? = null,
    @SerialName("sEOMetaKeyWords")
    val seoMetaKeyWords: String? = null,
    @SerialName("sEOName")
    val seoName: String? = null,
    @SerialName("sEOTitle")
    val seoTitle: String? = null,
    val secondaryUnit: String? = null,
    val secondaryUnitPrice: Int,
    val selectedProviderCode: String? = null,
    val selectedShopIsBulk: Boolean,
    val startStepValue: Double? = null,
    val unitPrice: Double
)