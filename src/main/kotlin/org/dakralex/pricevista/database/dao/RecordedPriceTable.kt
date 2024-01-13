package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.ArticleDao
import org.dakralex.pricevista.contracts.dao.RecordedPriceDao
import org.dakralex.pricevista.contracts.dao.StoreDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.RecordedPrice
import org.dakralex.pricevista.entities.RecordedPriceKey
import java.sql.ResultSet

class RecordedPriceTable(
    db: Database,
    private val stores: StoreDao,
    private val articles: ArticleDao
) : RecordedPriceDao,
    DatabaseTable<RecordedPrice, RecordedPriceKey>(
        db,
        "Recorded_Price",
        sequenceOf(
            "store_id",
            "article_id",
            "changed_at",
            "value"
        )
    ) {
    override fun isUnique(entity: RecordedPrice): (RecordedPrice) -> Boolean {
        val key =
            RecordedPriceKey(entity.store, entity.article, entity.changedAt)
        val matchesId = matchesWithId(key)

        return { e -> matchesId(e) }
    }

    override fun matchesWithId(id: RecordedPriceKey): (RecordedPrice) -> Boolean {
        return { e ->
            e.store.id == id.first.id
                    && e.article.id == id.second.id
                    && e.changedAt == id.third
        }
    }

    override fun mapFromResultSet(resultSet: ResultSet): RecordedPrice {
        return RecordedPrice(
            stores.findById(resultSet.getInt("store_id"))!!,
            articles.findById(resultSet.getInt("article_id"))!!,
            resultSet.getTimestamp("changed_at"),
            resultSet.getLong("value")
        )
    }

    override fun mapToPropArray(entry: RecordedPrice): Array<Any?> {
        return arrayOf(
            entry.store.id,
            entry.article.id,
            entry.changedAt,
            entry.value
        )
    }
}