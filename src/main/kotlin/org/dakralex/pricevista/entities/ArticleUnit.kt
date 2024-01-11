package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

typealias ArticleUnitId = Int

/**
 * The [ArticleUnit] entity describes a measurement unit that is used by
 * stores to quantify their articles, packages and volume sizes.
 */
data class ArticleUnit(
    val id: ArticleUnitId,

    /** Symbol or label of the measurement unit **/
    val label: String,

    /** English name of the measurement unit in singular **/
    val singularName: String = label,

    /** English name of the measurement unit in plural **/
    val pluralName: String = singularName + "s",
) : Entity {
    companion object : EntityComp<ArticleUnit> {
        override val tableName: String = "Article_Unit"
        override val insertStatement: String = """
                insert into $tableName (id, label, singular_name, plural_name)
                values (:id, :label, :singularName, :pluralName)
            """.trimIndent()

        override fun insertBatch(db: Database, entries: List<ArticleUnit>) {
            db.updateBatch(insertStatement, entries.map { entry ->
                arrayOf(
                    entry.id,
                    entry.label,
                    entry.singularName,
                    entry.pluralName
                )
            })
        }
    }

    override fun insert(db: Database) {
        db.update(insertStatement, id, label, singularName, pluralName)
    }
}
