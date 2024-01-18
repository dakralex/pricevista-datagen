package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity
import java.time.Instant

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
    var price: Long,

    /** Timestamp when the current price was changed at **/
    var changedAt: Instant,

    /** Timestamp when the current price was updated at **/
    var updatedAt: Instant = Instant.now(),
) : Entity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CurrentPrice

        if (store != other.store) return false
        if (article != other.article) return false

        return true
    }

    override fun hashCode(): Int {
        var result = store.hashCode()
        result = 31 * result + article.hashCode()
        return result
    }
}