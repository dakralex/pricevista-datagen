package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp
import java.util.*

typealias StoreArticleId = String

/**
 * The [StoreArticle] relation describes the mapping between the store's
 * internal article identifiers to the articles.
 */
data class StoreArticle(
    /** Store that holds the article **/
    val store: Store,

    /** Article identifier used by the store internally **/
    val storeArticleId: StoreArticleId,

    /** Article that the store's internal article identifier references **/
    var article: Article,

    /** Timestamp when the article was first discovered **/
    val since: Date
) : Entity {
    companion object : EntityComp<StoreArticle> {
        override val tableName: String = "Store_Article"
        override val insertStatement: String = """
                insert into $tableName (store_id, store_article_id, article_id, since)
                values (:storeId, :storeArticleId, :articleId, :since)
            """.trimIndent()
    }

    override fun insert(db: Database) {
        db.update(insertStatement, store.id, storeArticleId, article.id, since)
    }
}
