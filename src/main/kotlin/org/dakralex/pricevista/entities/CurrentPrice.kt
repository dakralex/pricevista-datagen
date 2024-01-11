package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp
import java.util.*

/**
 * The [CurrentPrice] relation describes the current price of the articles at
 * different stores.
 */
data class CurrentPrice(
    /** Store of the current price **/
    val store: Store,

    /** Article of the current price **/
    val article: Article,

    /** Value of the current price in the store's currency's minor unit **/
    var value: Long,

    /** Timestamp when the current price was changed at **/
    var changedAt: Date,

    /** Timestamp when the current price was updated at **/
    var updatedAt: Date,
) : Entity {
    companion object : EntityComp<CurrentPrice> {
        override val tableName: String = "Current_Price"
        override val insertStatement: String = """
                insert into $tableName (store_id, article_id, value, changed_at, updated_at)
                values (:storeId, :articleId, :value, :changedAt, :updatedAt)
            """.trimIndent()
    }

    override fun insert(db: Database) {
        db.update(
            insertStatement,
            store.id,
            article.id,
            value,
            changedAt,
            updatedAt
        )
    }
}
