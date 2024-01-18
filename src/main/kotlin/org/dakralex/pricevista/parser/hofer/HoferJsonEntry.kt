package org.dakralex.pricevista.parser.hofer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.dakralex.pricevista.contracts.parser.JsonEntry

@Serializable
data class HoferJsonEntry(
    /** Whether the article is available (Seems to always be true) **/
    val available: Boolean,

    /** Seems to always be null **/
    @SerialName("basketItemID")
    val basketItemId: String? = null,

    /**
     * Brand name of the article in uppercase letters
     *
     * Seems like anything in normal case is wrong data (e.g. product name included)
     */
    val brand: String? = null,

    /** Category information of the article **/
    val category: HoferJsonCategory,

    /** Category identifier of the article **/
    @SerialName("categoryID")
    val categoryId: Int,

    /** Kebap-cased category slug **/
    @SerialName("categorySEOName")
    val categorySeoName: String,

    /**
     * Deposit fee when the item is purchased
     *
     * TODO What does that mean? It seems to be only set on one item in the whole shop
     */
    val depositFee: Double? = null,

    /** @see [HoferJsonProductDetails.description] **/
    val description: String,

    /** @see [HoferJsonProductDetails.displayUnit] **/
    val displayUnit: String,

    /** Seems to always be null **/
    val galleryImageUrlList: String? = null,

    /**
     * Badge icon list for the article
     *
     * Seems to contain about three different URLs of the badges "BIO",
     * "AT product", and "Vegan".
     **/
    val icons: List<HoferJsonIcon>,

    /** Whether the item can be bought in bulk **/
    val isBulk: Boolean,

    /** Seems to always be null **/
    val isDiscountPD: String? = null,

    /** Seems to always be null **/
    val isDiscountPPDD: String? = null,

    /** Seems to always be false **/
    val isOffer: Boolean,

    /** Seems to always be false **/
    val isOneProductForFree: Boolean,

    /** Seems to always be false **/
    val isProductRokshDiscounted: Boolean,

    /** Seems to always be null **/
    val manufacturer: String? = null,

    /** TODO Find out the meaning behind this property (Seems to be a value between 5 and 24) **/
    val maxWeightStep: Int,

    /** URL address to an large image (1000x1000) of the article **/
    @SerialName("mediaUrlL")
    val mediaUrlLarge: String,

    /** URL address to an medium image (350x350) of the article **/
    @SerialName("mediaUrlM")
    val mediaUrlMedium: String,

    /** URL address to an thumbnail image (150x150) of the article **/
    @SerialName("mediaUrlS")
    val mediaUrlSmall: String,

    /**
     * The minimum price for the article
     *
     * Seems to always be the same as the [price], but could be lowered if
     * there is an sale (just guessing)
     */
    val minPrice: Double,

    /**
     * The minimum bulk price (or what the item would cost per [priceUnitType])
     *
     * Seems to always be the same as the [unitPrice], but could be lowered if
     * there is an sale (just guessing)
     */
    val minUnitPrice: Double,

    /** TODO Find out the meaning behind this property **/
    val minWeightStep: Double,

    /** TODO Find out the meaning behind this property **/
    val offerValidTo: String,

    /** Seems to always be null **/
    val originalPriceIfOffer: String? = null,

    /** Seems to always be null **/
    val originalUnitPriceIfOffer: String? = null,

    /** Price for the article **/
    val price: Double,

    /** Unit (prefixed optionally with the quantity) to be used for the bulk price **/
    val priceUnitType: String? = null,

    val productDetails: HoferJsonProductDetails,

    /** Internal identifier for the product **/
    @SerialName("productID")
    val productId: Int,

    /** Seems to always be a list of at most a single media URL address that equals [mediaUrlLarge] */
    val productMediaList: List<HoferJsonProductMediaListItem>,

    /** Product name with the brand, article name and sometimes the quantity **/
    val productName: String,

    /** TODO Find out what's in there **/
    val productProvider: List<HoferJsonProductProviderItem>,

    /** Identifier of the product provider (Seems to always be value 14) **/
    @SerialName("productProviderID")
    val productProviderId: Int,

    /** Whether the provider is available **/
    val providerAvailable: Boolean,

    /** Seems to always be an empty array **/
    val providerDepositProductDtoList: List<String>,

    /** TODO Find out what the number means (seems to be values between 5 and 100 in step size 5) **/
    val providerOrderNumber: Int,

    /** TODO Find out what this value means (see 2024-01-02 export) **/
    val rokshDiscountBasketValue: Int? = null,

    /** TODO Find out what this value means (see 2024-01-02 export) **/
    val rokshDiscountLevel: Int? = null,

    /** TODO Find out what this value means (see 2024-01-02 export) **/
    val rokshDiscountPrice: Double? = null,

    /** Seems to always be null **/
    @SerialName("sEOMetaDescripton")
    val seoMetaDescription: String? = null,

    /** Seems to always be null **/
    @SerialName("sEOMetaKeyWords")
    val seoMetaKeyWords: String? = null,

    /** TODO Find out if this field equals productName with whitespace replaced by hyphens **/
    @SerialName("sEOName")
    val seoName: String? = null,

    /** Seems to always be null **/
    @SerialName("sEOTitle")
    val seoTitle: String? = null,

    /** Seems to always be null **/
    val secondaryUnit: String? = null,

    /** Seems to always be 0 (zero) **/
    val secondaryUnitPrice: Int,

    /** Seems to always be null **/
    val selectedProviderCode: String? = null,

    /** @see [isBulk] **/
    val selectedShopIsBulk: Boolean,

    /** TODO Find out what the number means **/
    val startStepValue: Double? = null,

    /** Bulk price value (or what the item would cost per [priceUnitType]) **/
    val unitPrice: Double
) : JsonEntry