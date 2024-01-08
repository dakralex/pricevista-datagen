package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import java.util.*

/**
 * The [StoreArticleMap] relation describes the mapping between the store's
 * internal article identifiers to the articles.
 */
data class StoreArticleMap(
    /** Store that holds the article **/
    val store: Store,

    /** Article identifier used by the store internally **/
    val storeArticleId: String,

    /** Article that the store's internal article identifier references **/
    var article: Article,

    /** Timestamp when the article was first discovered **/
    val since: Date
) : Entity {
    companion object {
        const val tableName: String = "Store_Article_Map"
        const val insertStatement: String =
            """insert into $tableName (store_id, store_article_id, article_id, since) values (:storeId, :storeArticleId, :articleId, :since)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, store.id, storeArticleId, article.id, since)
    }
}
