package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity
import java.util.*

typealias CurrentPriceKey = Pair<Store, Article>

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
) : Entity