package org.dakralex.pricevista.entities

/**
 * The [Brand] entity describes a specialization of a [Company], which
 * functions as a brand that brand articles that are sold in stores.
 */
data class Brand(
    /** Company generalization of the brand **/
    val company: Company,

    /** URL address to a logo **/
    var logoUrl: String? = null
)
