package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [Retailer] entity describes a specialization of a [Company], which
 * functions as a retailer of one or more stores that sell articles.
 */
data class Retailer(
    /** Company of the retailer **/
    val company: Company,

    /** URL address to the main online presence of the retailer **/
    val websiteUrl: String? = null
) : Entity {
    companion object {
        const val tableName: String = "Retailer"
        const val insertStatement: String =
            """insert into $tableName (company_id, website_url) values (:companyId, :websiteUrl)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, company.id, websiteUrl)
    }
}
