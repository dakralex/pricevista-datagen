package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.ArticleDao
import org.dakralex.pricevista.contracts.dao.CurrentPriceDao
import org.dakralex.pricevista.contracts.dao.StoreDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.CurrentPrice
import org.dakralex.pricevista.entities.CurrentPriceKey
import java.sql.ResultSet
import java.sql.Timestamp

class CurrentPriceTable(
    db: Database,
    private val stores: StoreDao,
    private val articles: ArticleDao
) : CurrentPriceDao,
    DatabaseTable<CurrentPrice, CurrentPriceKey>(
        db,
        "Current_Price",
        sequenceOf(
            "store_id",
            "article_id",
            "value",
            "changed_at",
            "updated_at"
        )
    ) {
    override fun isUnique(entity: CurrentPrice): (CurrentPrice) -> Boolean {
        // TODO Make the CurrentPrice transitive when no id was given
        return { e -> e.store.id == entity.store.id && e.article.id == entity.article.id }
    }

    override fun matchesWithId(id: CurrentPriceKey): (CurrentPrice) -> Boolean {
        return { e -> e.store.id == id.first.id && e.article.id == id.second.id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): CurrentPrice {
        return CurrentPrice(
            stores.findById(resultSet.getInt("store_id"))!!,
            articles.findById(resultSet.getInt("article_id"))!!,
            resultSet.getLong("value"),
            resultSet.getTimestamp("changed_at").toInstant(),
            resultSet.getTimestamp("updated_at").toInstant()
        )
    }

    override fun mapToPropArray(entry: CurrentPrice): Array<Any?> {
        return arrayOf(
            entry.store.id,
            entry.article.id,
            entry.value,
            Timestamp.from(entry.changedAt),
            Timestamp.from(entry.updatedAt)
        )
    }
}