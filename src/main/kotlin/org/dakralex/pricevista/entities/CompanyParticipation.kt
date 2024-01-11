package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

/**
 * The [CompanyParticipation] relation describes the relationship between
 * stakeholders and companies they take interest in.
 */
data class CompanyParticipation(
    /** Stakeholder that takes interest in a company */
    val stakeholder: Company,

    /** The company that the stakeholder takes interest in */
    val company: Company
) : Entity {
    companion object : EntityComp<CompanyParticipation> {
        override val tableName: String = "Company_Participation"
        override val insertStatement: String = """
                insert into $tableName (stakeholder_id, company_id)
                values (:stakeholderId, :companyId)
            """.trimIndent()
    }

    override fun insert(db: Database) {
        db.update(insertStatement, stakeholder.id, company.id)
    }
}
