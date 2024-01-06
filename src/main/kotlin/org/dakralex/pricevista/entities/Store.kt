package org.dakralex.pricevista.entities

/**
 * The [Store] entity describes a place where a retailer distributes articles
 * by advertising, stocking, and selling them over that platform. This could
 * be a physical place (e.g. a shop) or a virtual place (e.g. online store).
 *
 * In the case that this store is an online store, the location should at least
 * define the country the online store is intended for.
 */
data class Store(
    val id: Int? = null,

    /** Retailer that runs the store **/
    val retailer: Retailer,

    /** Location of the store **/
    var place: Place,

    /** Currency of the store **/
    var currency: Currency,

    /** Language of the store **/
    var language: Language
)
