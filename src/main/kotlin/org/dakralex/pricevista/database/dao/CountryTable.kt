package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.CountryDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Country
import org.dakralex.pricevista.entities.CountryId
import org.dakralex.pricevista.entities.data.ECountry
import java.sql.ResultSet

class CountryTable(
    db: Database
) : CountryDao,
    DatabaseTable<Country, CountryId>(
        db,
        "Country",
        sequenceOf(
            "id",
            "alpha2",
            "alpha3",
            "name"
        )
    ) {

    override fun initialize(): Boolean {
        if (super.initialize()) {
            addBatch(ECountry.entries.asSequence().map(ECountry::country))
            return true
        }

        return false
    }

    override fun isUnique(entity: Country): (Country) -> Boolean {
        return { e -> e.id == entity.id }
    }

    override fun matchesWithId(id: CountryId): (Country) -> Boolean {
        return { e -> e.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Country {
        return Country(
            resultSet.getInt("id"),
            resultSet.getString("alpha2"),
            resultSet.getString("alpha3"),
            resultSet.getString("name")
        )
    }

    override fun mapToPropArray(entry: Country): Array<Any?> {
        return arrayOf(
            entry.id,
            entry.alpha2,
            entry.alpha3,
            entry.name
        )
    }
}