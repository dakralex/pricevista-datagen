package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.CompanyDao
import org.dakralex.pricevista.contracts.dao.PlaceDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Company
import org.dakralex.pricevista.entities.CompanyId
import org.dakralex.pricevista.entities.data.ECompany
import java.sql.ResultSet

class CompanyTable(
    db: Database,
    private val places: PlaceDao
) : CompanyDao,
    DatabaseTable<Company, CompanyId>(
        db,
        "Company",
        sequenceOf(
            "id",
            "long_name",
            "short_name",
            "place_id"
        )
    ) {

    override fun initialize(): Boolean {
        if (super.initialize()) {
            addBatch(ECompany.entries.asSequence().map(ECompany::company))
            return true
        }

        return false
    }

    override fun isUnique(entity: Company): (Company) -> Boolean {
        return if (entity.id == null) { e ->
            e.shortName == entity.shortName
        } else { e ->
            e.id == entity.id
        }
    }

    override fun matchesWithId(id: CompanyId): (Company) -> Boolean {
        return { e -> e.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Company {
        return Company(
            resultSet.getInt("id"),
            resultSet.getString("long_name"),
            resultSet.getString("short_name"),
            places.findById(resultSet.getInt("place_id"))
        )
    }

    override fun mapToPropArray(entry: Company): Array<Any?> {
        return arrayOf(
            entry.id,
            entry.longName,
            entry.shortName,
            entry.place?.id
        )
    }
}