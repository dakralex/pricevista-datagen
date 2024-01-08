package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

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
    companion object {
        const val tableName: String = "Company_Participation"
        const val insertStatement: String =
            """insert into $tableName (stakeholder_id, company_id) values (:stakeholderId, :companyId)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, stakeholder.id, company.id)
    }
}
