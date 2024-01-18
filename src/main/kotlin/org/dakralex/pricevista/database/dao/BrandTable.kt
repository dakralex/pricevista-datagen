package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.BrandDao
import org.dakralex.pricevista.contracts.dao.CompanyDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Brand
import org.dakralex.pricevista.entities.BrandId
import java.sql.ResultSet

class BrandTable(
    db: Database,
    private val companies: CompanyDao
) : BrandDao,
    DatabaseTable<Brand, BrandId>(
        db,
        "Brand",
        sequenceOf(
            "company_id",
            "logo_url"
        )
    ) {

    override fun matchesWithId(id: BrandId): (Brand) -> Boolean {
        return { e -> e.company.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Brand {
        return Brand(
            companies.findById(resultSet.getInt("company_id"))!!,
            resultSet.getString("logo_url")
        )
    }

    override fun mapToPropArray(entry: Brand): Array<Any?> {
        return arrayOf(
            entry.company.id,
            entry.logoUrl
        )
    }

    override fun findByShortName(shortName: String): Brand? {
        val entityWithName = { e: Brand -> e.company.shortName == shortName }

        return cleanEntries.find(entityWithName)
            ?: newEntries.find(entityWithName)
    }
}