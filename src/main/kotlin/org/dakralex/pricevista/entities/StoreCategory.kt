package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

typealias StoreCategoryId = String

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
) : Entity {
    companion object : EntityComp<StoreCategory> {
        override val tableName: String = "Store_Category"
        override val insertStatement: String = """
                insert into $tableName (store_id, store_category_id, category_id)
                values (:storeId, :storeCategoryId, :categoryId)
            """.trimIndent()
    }

    override fun insert(db: Database) {
        db.update(insertStatement, store.id, storeCategoryId, category.id)
    }
}
