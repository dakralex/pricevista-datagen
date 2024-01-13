package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable
import org.dakralex.pricevista.contracts.parser.JsonEntry
import org.dakralex.pricevista.parser.DoubleAsStringSerializer

@Serializable
data class BillaJsonEntry(
    val ageRequiredInMonths: Int? = null,
    @Serializable(with = DoubleAsStringSerializer::class)
    val amount: Double,
    val badges: List<BillaJsonBadges>? = null,
    val brand: BillaJsonBrand? = null,
    val category: String? = null,
    val countryOfOrigin: String? = null,
    val depositType: String? = null,
    val descriptionShort: String? = null,
    val descriptionLong: String? = null,
    val drainedWeight: Double? = null,
    val images: List<String>,
    val mappedCategory: String,
    val maxQuantity: Int? = null,
    val medical: Boolean,
    val minQuantity: Int? = null,
    val name: String,
    val packageLabel: String? = null,
    val packageLabelKey: String? = null,
    val parentCategories: List<List<BillaJsonParentCategory>>,
    val preparationTime: Int? = null,
    val price: BillaJsonPriceInfo,

    /** Internal article identifier in UUIDv4 format **/
    val productId: String,
    val productMarketing: String? = null,
    val purchased: Boolean,
    val quantityStepSize: Int? = null,
    val sku: String,
    val slug: String,
    val upsellSku: String? = null,
    val volumeLabelKey: String,
    val volumeLabelLong: String,
    val volumeLabelShort: String? = null,
    val weight: Double,

    /** Whether the article is sold by weighing it and calculating the price per unit (e.g. per kilogram) **/
    val weightArticle: Boolean,
    val weightPerPiece: Double,
    val weightPieceArticle: Boolean,
) : JsonEntry