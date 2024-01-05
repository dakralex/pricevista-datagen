package org.dakralex.pricevista.entities

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
)
