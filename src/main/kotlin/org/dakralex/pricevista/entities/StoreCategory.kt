package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias StoreCategoryId = String
typealias StoreCategoryKey = Pair<Store, StoreCategoryId>

/**
 * The [StoreCategory] relation describes the mapping between the store's
 * internal category identifiers to the categories.
 */
data class StoreCategory(
    /** Store that has the category **/
    val store: Store,

    /** Category identifier used by the store internally **/
    val storeCategoryId: StoreCategoryId,

    /** Category that the store's internal category identifier references **/
    var category: Category
) : Entity