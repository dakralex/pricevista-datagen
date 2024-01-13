package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.CompanyDao
import org.dakralex.pricevista.contracts.dao.CompanyParticipationDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.CompanyParticipation
import org.dakralex.pricevista.entities.CompanyParticipationKey
import java.sql.ResultSet

class CompanyParticipationTable(
    db: Database,
    private val companies: CompanyDao
) : CompanyParticipationDao,
    DatabaseTable<CompanyParticipation, CompanyParticipationKey>(
        db,
        "Company_Participation",
        listOf("stakeholder_id", "company_id")
    ) {
    override fun isUnique(entity: CompanyParticipation): (CompanyParticipation) -> Boolean {
        return { e ->
            e.stakeholder.id == entity.stakeholder.id
                    && e.company.id == entity.company.id
        }
    }

    override fun matchesWithId(id: CompanyParticipationKey): (CompanyParticipation) -> Boolean {
        return { e -> e.stakeholder.id == id.first.id && e.company.id == id.second.id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): CompanyParticipation {
        return CompanyParticipation(
            companies.findById(resultSet.getInt("stakeholder_id"))!!,
            companies.findById(resultSet.getInt("company_id"))!!
        )
    }

    override fun mapToPropArray(entry: CompanyParticipation): Array<Any?> {
        return arrayOf(
            entry.stakeholder.id,
            entry.company.id
        )
    }
}