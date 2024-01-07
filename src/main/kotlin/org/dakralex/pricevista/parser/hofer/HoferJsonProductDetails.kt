package org.dakralex.pricevista.parser.hofer

import kotlinx.serialization.Serializable

@Serializable
data class HoferJsonProductDetails(
    /** Seems to always be null **/
    val alcoholStr: String? = null,

    /** Comma-separated list of allergenic items **/
    val allergenic: String? = null,

    /** Seems to always be an empty string **/
    val consumption: String,

    /** Small information on the product followed by a long legal text about what it could be **/
    val description: String,

    /** String of the unit (either "Beutel", "Flasche", "Kg", "Packung", or "Stück") **/
    val displayUnit: String,

    /** Comma-separated list of ingredients from the package **/
    val ingredients: String? = null,

    /** Seems to always be null **/
    val manufacturer: String? = null,

    /** Comma-separated list of origin countries **/
    val originCountry: String? = null,

    /** Seems to always be an empty string **/
    val packageType: String,

    /** Seems to always be an empty string **/
    val producerAddress: String,

    /** Seems to always be null **/
    val promotion: String? = null,

    /** Seems to always be null **/
    val secondaryUnit: String? = null,

    /** Seems to always be 0 (zero) **/
    val secondaryUnitPrice: Int,

    /** Seems to always be an empty string **/
    val serviceDepartment: String,

    /** Seems to always be an empty string **/
    val storing: String,

    /** Seems to always be an empty string **/
    val supplierAddress: String,

    /** Seems to always be an empty string **/
    val supplierPhone: String,

    /** Seems to always be an empty string **/
    val supplierWeb: String,

    /** Seems to always be null **/
    val textualNutrition: String? = null
)