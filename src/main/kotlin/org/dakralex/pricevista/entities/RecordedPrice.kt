package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity
import java.time.Instant

typealias RecordedPriceKey = Triple<Store, Article, Instant>

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
    val changedAt: Instant,

    /** Value of the recorded price in the store's currency's minor unit **/
    var price: Long,
) : Entity