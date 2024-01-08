package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

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
        const val tableName: String = "Brand"
        const val insertStatement: String =
            """insert into $tableName (company_id, logo_url) values (:companyId, :logoUrl)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, company.id, logoUrl)
    }
}
