package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.CurrencyDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Currency
import org.dakralex.pricevista.entities.CurrencyId
import org.dakralex.pricevista.entities.data.ECurrency
import java.sql.ResultSet

class CurrencyTable(
    db: Database
) : CurrencyDao,
    DatabaseTable<Currency, CurrencyId>(
        db,
        "Currency",
        listOf("id", "alpha3", "scale", "symbol", "minor", "name")
    ) {

    override fun initialize(): Boolean {
        if (super.initialize()) {
            addBatch(ECurrency.entries.asSequence().map(ECurrency::currency))
            return true
        }

        return false
    }

    override fun isUnique(entity: Currency): (Currency) -> Boolean {
        return { e ->
            e.id == entity.id ||
                    e.alpha3 == entity.alpha3 &&
                    e.name == entity.name
        }
    }

    override fun matchesWithId(id: CurrencyId): (Currency) -> Boolean {
        return { e -> e.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Currency {
        return Currency(
            resultSet.getInt("id"),
            resultSet.getString("alpha3"),
            resultSet.getInt("scale"),
            resultSet.getString("symbol"),
            resultSet.getString("minor"),
            resultSet.getString("name")
        )
    }

    override fun mapToPropArray(entry: Currency): Array<Any?> {
        return arrayOf(
            entry.id,
            entry.alpha3,
            entry.scale,
            entry.symbol,
            entry.minor,
            entry.name
        )
    }
}