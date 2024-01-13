package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.*
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Store
import org.dakralex.pricevista.entities.StoreId
import java.sql.ResultSet

class StoreTable(
    db: Database,
    private val retailers: RetailerDao,
    private val places: PlaceDao,
    private val currencies: CurrencyDao,
    private val languages: LanguageDao
) : StoreDao,
    DatabaseTable<Store, StoreId>(
        db,
        "Store",
        sequenceOf(
            "id",
            "retailer_id",
            "place_id",
            "currency_id",
            "language_id"
        )
    ) {
    override fun isUnique(entity: Store): (Store) -> Boolean {
        return { e -> e.id == entity.id }
    }

    override fun matchesWithId(id: StoreId): (Store) -> Boolean {
        return { e -> e.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Store {
        return Store(
            resultSet.getInt("id"),
            retailers.findById(resultSet.getInt("retailer_id"))!!,
            places.findById(resultSet.getInt("place_id"))!!,
            currencies.findById(resultSet.getInt("currency_id"))!!,
            languages.findById(resultSet.getInt("language_id"))!!
        )
    }

    override fun mapToPropArray(entry: Store): Array<Any?> {
        return arrayOf(
            entry.id,
            entry.retailer.company.id,
            entry.place.id,
            entry.currency.id,
            entry.language.id
        )
    }
}