package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

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
    companion object : EntityComp<Brand> {
        override val tableName: String = "Brand"
        override val insertStatement: String = """
                insert into $tableName (company_id, logo_url)
                values (:companyId, :logoUrl)
            """.trimIndent()
    }

    override fun insert(db: Database) {
        db.update(insertStatement, company.id, logoUrl)
    }
}
