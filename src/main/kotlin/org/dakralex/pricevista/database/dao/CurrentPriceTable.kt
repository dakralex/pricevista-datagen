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
            "price",
            "changed_at",
            "updated_at"
        )
    ) {
    override val insertFullStmt = """
        merge into Current_Price target
            using ( select :storeId   as store_id,
                           :articleId as article_id,
                           :price     as price,
                           :changedAt as changed_at,
                           :updatedAt as updated_at
                    from dual ) source
            on (target.store_id = source.store_id and
                target.article_id = source.article_id)
            when matched then
                update
                set price      = source.price,
                    changed_at = source.changed_at,
                    updated_at = source.updated_at
                where target.price <> source.price
            when not matched then
                insert
                values (source.store_id, source.article_id, source.price,
                        source.changed_at, source.updated_at)
    """.trimIndent()

    override fun initialize(): Boolean {
        if (isNotExistent) {
            return createTable()
        }

        return false
    }

    override fun matchesWithId(id: CurrentPriceKey): (CurrentPrice) -> Boolean {
        return { e -> e.store.id == id.first.id && e.article.id == id.second.id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): CurrentPrice {
        return CurrentPrice(
            stores.findById(resultSet.getInt("store_id"))!!,
            articles.findById(resultSet.getInt("article_id"))!!,
            resultSet.getLong("price"),
            resultSet.getTimestamp("changed_at").toInstant(),
            resultSet.getTimestamp("updated_at").toInstant()
        )
    }

    override fun mapToPropArray(entry: CurrentPrice): Array<Any?> {
        return arrayOf(
            entry.store.id,
            entry.article.id,
            entry.price,
            Timestamp.from(entry.changedAt),
            Timestamp.from(entry.updatedAt)
        )
    }
}