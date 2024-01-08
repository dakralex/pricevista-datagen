package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import java.util.*

/**
 * The [RecordedPrice] relation describes the historical prices of articles
 * at specific stores.
 */
data class RecordedPrice(
    /** Store of the recorded price **/
    val store: Store,

    /** Article of the recorded price **/
    val article: Article,

    /** Timestamp when the price was recorded **/
    val changedAt: Date,

    /** Value of the recorded price in the store's currency's minor unit **/
    var value: Long,
) : Entity {
    companion object {
        const val tableName: String = "Recorded_Price"
        const val insertStatement: String =
            """insert into $tableName (store_id, article_id, changed_at, value) values (:storeId, :articleId, :changedAt, :value)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, store.id, article.id, changedAt, value)
    }
}
