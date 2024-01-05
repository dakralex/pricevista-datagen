package org.dakralex.pricevista.entities

/**
 * The [StoreCategoryMap] relation describes the mapping between the store's
 * internal category identifiers to the categories.
 */
data class StoreCategoryMap(
    /** Store that has the category **/
    val store: Store,

    /** Category identifier used by the store internally **/
    val storeCategoryId: String,

    /** Category that the store's internal category identifier references **/
    var category: Category
)
