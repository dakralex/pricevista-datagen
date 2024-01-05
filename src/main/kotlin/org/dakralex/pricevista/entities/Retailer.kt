package org.dakralex.pricevista.entities

/**
 * The [Retailer] entity describes a specialization of a [Company], which
 * functions as a retailer of one or more stores that sell articles.
 */
data class Retailer(
    /** Company generalization of the retailer **/
    val company: Company,

    /** Currency in which the retailer sets their prices **/
    val currency: Currency,

    /** URL address to the main online presence of the retailer **/
    val websiteUrl: String? = null
)
