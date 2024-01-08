package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

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
) : Entity {
    companion object {
        const val tableName: String = "Store_Category_Map"
        const val insertStatement: String =
            """insert into $tableName (store_id, store_category_id, category_id) values (:storeId, :storeCategoryId, :categoryId)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, store.id, storeCategoryId, category.id)
    }
}
