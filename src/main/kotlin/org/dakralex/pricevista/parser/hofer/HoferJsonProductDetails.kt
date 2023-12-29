package org.dakralex.pricevista.parser.hofer

import kotlinx.serialization.Serializable

@Serializable
data class HoferJsonProductDetails(
    val alcoholStr: String? = null,
    val allergenic: String? = null,
    val consumption: String,
    val description: String,
    val displayUnit: String,
    val ingredients: String? = null,
    val manufacturer: String? = null,
    val originCountry: String? = null,
    val packageType: String,
    val producerAddress: String,
    val promotion: String? = null,
    val secondaryUnit: String? = null,
    val secondaryUnitPrice: Int,
    val serviceDepartment: String,
    val storing: String,
    val supplierAddress: String,
    val supplierPhone: String,
    val supplierWeb: String,
    val textualNutrition: String? = null
)