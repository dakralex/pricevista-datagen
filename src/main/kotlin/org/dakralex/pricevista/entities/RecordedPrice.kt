package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity
import java.util.*

typealias RecordedPriceKey = Triple<Store, Article, Date>

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
) : Entity