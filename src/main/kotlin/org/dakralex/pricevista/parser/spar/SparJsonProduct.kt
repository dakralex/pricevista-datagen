package org.dakralex.pricevista.parser.spar

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SparJsonProduct(
    @SerialName("actual-price") val actualPrice: Double? = null,
    @SerialName("additional-quantity") val additionalQuantity: String? = null,
    @SerialName("alcohol-age") val alcoholAge: String? = null,
    @SerialName("approx-weight-product") val approxWeightProduct: String? = null,
    @SerialName("area") val area: String? = null,
    @SerialName("badge-icon") val badgeIcon: String? = null,
    @SerialName("badge-names") val badgeNames: String? = null,
    @SerialName("badge-short-name") val badgeShortName: String? = null,
    @SerialName("best-price") val bestPrice: Double,
    @SerialName("biolevel") val bioLevel: String? = null,

    /**
     * The list of brand names in reverse order, that is the sub brands come
     * first (e.g. [ "Professional", "SIMPEX" ]). In that case, the [title]
     * field is the whole brand name (e.g. "SIMPEX Professional") and the
     * [ecrBrand] field is the main brand (e.g. "SIMPEX")
     *
     * If this field is set to null, it is either unbranded or the brand is
     * included in the beginning of [title].
     */
    @SerialName("brand") val brand: List<String>? = null,
    @SerialName("categories") val categories: List<String>? = null,
    @SerialName("category-facet") val categoryFacet: String? = null,
    @SerialName("category-id") val categoryId: List<String>? = null,
    @SerialName("category-names") val categoryNames: String? = null,
    @SerialName("category-path") val categoryPath: List<List<String>>? = null,
    @SerialName("code-internal") val codeInternal: String,
    @SerialName("color-filter") val colorFilter: List<String>? = null,
    @SerialName("created-at") val createdAt: String,
    @SerialName("customer-general-info-name") val customerGeneralInfoName: List<String>,
    @SerialName("customer-info") val customerInfo: List<String>? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("ecr-brand") val ecrBrand: String? = null,
    @SerialName("ecr-type") val ecrType: String? = null,
    @SerialName("fabric-base") val fabricBase: String? = null,
    @SerialName("fat-content") val fatContent: String? = null,
    @SerialName("finalsalescheckok") val finalSalesCheckOk: String? = null,
    @SerialName("image-url") val imageUrl: String,
    @SerialName("is-new") val isNew: String,
    @SerialName("is-on-promotion") val isOnPromotion: String,
    @SerialName("is-restaurant-product") val isRestaurantProduct: String,
    @SerialName("item-type") val itemType: String,
    @SerialName("land") val country: String? = null,
    @SerialName("marketing-text") val marketingText: String? = null,

    /**
     * Name of the product as it is usually displayed at the shop bon.
     *
     * e.g. "SIMPEX Pfanne  28 cm schwarz    GVE 4"
     */
    @SerialName("name") val name: String,
    @SerialName("packing-type") val packingType: String? = null,
    @SerialName("pos-purchasable") val posPurchasable: List<String>? = null,
    @SerialName("pos-visible") val posVisible: List<String>? = null,
    @SerialName("price") val price: Double,
    @SerialName("price-per-unit") val pricePerUnit: String,
    @SerialName("product-number") val productNumber: String,
    @SerialName("promotion-most-likely-text") val promotionMostLikelyText: String? = null,
    @SerialName("promotion-text") val promotionText: String? = null,
    @SerialName("quantity-selector") val quantitySelector: String? = null,
    @SerialName("recipe") val recipe: String? = null,
    @SerialName("region") val region: String? = null,
    @SerialName("regular-price") val regularPrice: Double,
    @SerialName("relevantfororder") val relevantForOrder: String,
    @SerialName("sales-unit") val salesUnit: String,
    @SerialName("shop-source") val shopSource: String,

    /**
     * This field has different meanings:
     *
     * - if this is equal to [shortDescription2], then it is the product name
     *   without brand but possibly includes a quantity descriptor
     * - if this is set to null, then it can be expected that
     *   [shortDescription2] and [shortDescription3] are also null
     * - if this is equal to [shortDescription3], then both have quantity
     *   descriptor with the format "<size> <unit>" and [title] is
     */
    @SerialName("short-description") val shortDescription: String? = null,

    /**
     * This field has different meanings:
     *
     * - if this is set to null, then [shortDescription] and
     *   [shortDescription3] have the quantity descriptor
     * - if this equal to [shortDescription], then it is the product name
     *   without brand but possibly includes a quantity descriptor
     */
    @SerialName("short-description-2") val shortDescription2: String? = null,

    /**
     * This field has different meanings:
     *
     * - if this is set to null, then [title] is the brand name and
     *   [shortDescription] and [shortDescription2] are the product name with
     *   a quantity descriptor
     * - otherwise it is the quantity size descriptor with the format
     *   "<size> <unit>"
     */
    @SerialName("short-description-3") val shortDescription3: String? = null,
    @SerialName("sort") val sort: String? = null,
    @SerialName("stock-status") val stockStatus: String,
    @SerialName("target-group") val targetGroup: String? = null,
    @SerialName("taste") val taste: String? = null,

    /**
     * This field has different meanings:
     *
     * - if [shortDescription] and [shortDescription3] have the same value, then
     *   this field is the product name
     * - otherwise it is the whole brand name (including sub brands)
     *
     * Usually in the last case, [shortDescription] and [shortDescription2]
     * have the same value.
     */
    @SerialName("title") val title: String,

    /** Relative url path to the product page **/
    @SerialName("url") val url: String,
    @SerialName("year") val year: String? = null
)