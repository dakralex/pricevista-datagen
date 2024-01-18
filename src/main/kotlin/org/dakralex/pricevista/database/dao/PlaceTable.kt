package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.CountryDao
import org.dakralex.pricevista.contracts.dao.PlaceDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Place
import org.dakralex.pricevista.entities.PlaceId
import org.dakralex.pricevista.entities.data.EPlace
import java.sql.ResultSet

class PlaceTable(
    db: Database,
    private val countries: CountryDao
) : PlaceDao,
    DatabaseTable<Place, PlaceId>(
        db,
        "Place",
        sequenceOf(
            "id",
            "country_id",
            "admin_area",
            "locality",
            "postal_code",
            "street_address"
        )
    ) {

    override fun initialize(): Boolean {
        if (super.initialize()) {
            addBatch(EPlace.entries.asSequence().map(EPlace::place))
            return true
        }

        return false
    }

    override fun matchesWithId(id: PlaceId): (Place) -> Boolean {
        return { e -> e.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Place {
        return Place(
            resultSet.getInt("id"),
            countries.findById(resultSet.getInt("country_id"))!!,
            resultSet.getString("admin_area"),
            resultSet.getString("locality"),
            resultSet.getString("postal_code"),
            resultSet.getString("street_address")
        )
    }

    override fun mapToPropArray(entry: Place): Array<Any?> {
        return arrayOf(
            entry.id,
            entry.country.id,
            entry.adminArea,
            entry.locality,
            entry.postalCode,
            entry.streetAddress
        )
    }
}