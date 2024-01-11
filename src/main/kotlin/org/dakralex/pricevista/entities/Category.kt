package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

typealias CategoryId = Int

/**
 * The [Category] entity describes a category that articles can belong to.
 */
data class Category(
    val id: CategoryId? = null,

    /** Name of the category **/
    var name: String,

    /** Description of the category **/
    var description: String? = null
) : Entity {
    companion object : EntityComp<Category> {
        override val tableName: String = "Category"
        override val insertStatement: String = """
                insert into $tableName (id, name, description)
                values (:id, :name, :description)
            """.trimIndent()
    }

    override fun insert(db: Database) {
        db.update(insertStatement, id, name, description)
    }
}
