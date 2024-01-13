package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity
import java.util.*

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
    val since: Date
) : Entity