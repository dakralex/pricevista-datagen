package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable
import org.dakralex.pricevista.parser.DoubleAsStringSerializer

@Serializable
data class JsonBillaEntry(
    val ageRequiredInMonths: Int? = null,
    @Serializable(with = DoubleAsStringSerializer::class)
    val amount: Double,
    val badges: List<JsonBillaBadges>? = null,
    val brand: JsonBillaBrand? = null,
    val category: String? = null,
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
    val parentCategories: List<List<JsonBillaParentCategory>>,
    val preparationTime: Int? = null,
    val price: JsonBillaPriceInfo,
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
    val weightArticle: Boolean,
    val weightPerPiece: Double,
    val weightPieceArticle: Boolean,
)