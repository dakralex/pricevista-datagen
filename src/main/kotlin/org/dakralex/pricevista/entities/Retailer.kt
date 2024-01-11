package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

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
) : Entity {
    companion object : EntityComp<Retailer> {
        override val tableName: String = "Retailer"
        override val insertStatement: String = """
                insert into $tableName (company_id, website_url)
                values (:companyId, :websiteUrl)
            """.trimIndent()

        override fun insertBatch(db: Database, entries: List<Retailer>) {
            db.updateBatch(insertStatement, entries.map { entry ->
                arrayOf(entry.company.id, entry.websiteUrl)
            })
        }
    }

    override fun insert(db: Database) {
        db.update(insertStatement, company.id, websiteUrl)
    }
}
