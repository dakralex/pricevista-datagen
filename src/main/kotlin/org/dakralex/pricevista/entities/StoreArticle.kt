package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity
import java.time.Instant

typealias StoreArticleId = String
typealias StoreArticleKey = Pair<Store, StoreArticleId>

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
    val since: Instant
) : Entity {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StoreArticle

        if (store != other.store) return false
        if (storeArticleId != other.storeArticleId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = store.hashCode()
        result = 31 * result + storeArticleId.hashCode()
        return result
    }
}