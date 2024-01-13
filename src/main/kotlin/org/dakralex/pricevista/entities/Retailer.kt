package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias RetailerId = CompanyId

/**
 * The [Retailer] entity describes a specialization of a [Company], which
 * functions as a retailer of one or more stores that sell articles.
 */
data class Retailer(
    /** Company of the retailer **/
    val company: Company,

    /** URL address to the main online presence of the retailer **/
    val websiteUrl: String? = null
) : Entity