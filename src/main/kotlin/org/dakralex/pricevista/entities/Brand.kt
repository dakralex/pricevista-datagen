package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias BrandId = CompanyId

/**
 * The [Brand] entity describes a specialization of a [Company], which
 * functions as a brand that brand articles that are sold in stores.
 */
data class Brand(
    /** Company of the brand **/
    val company: Company,

    /** URL address to a logo **/
    var logoUrl: String? = null
) : Entity {
    companion object {
        fun fromCompany(company: Company): Brand {
            return Brand(company)
        }
    }
}