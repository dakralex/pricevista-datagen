package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

/**
 * The [MeasurementUnit] entity describes a measurement unit that is used by
 * stores to quantify their package and volume sizes.
 */
data class MeasurementUnit(
    val id: Int,

    /** Symbol or label of the measurement unit **/
    val label: String,

    /** English name of the measurement unit in singular **/
    val singularName: String = label,

    /** English name of the measurement unit in plural **/
    val pluralName: String = singularName + "s",
) : Entity {
    companion object : EntityComp<MeasurementUnit> {
        override val tableName: String = "Measurement_Unit"
        override val insertStatement: String = """
                insert into $tableName (id, label, singular_name, plural_name)
                values (:id, :label, :singularName, :pluralName)
            """.trimIndent()

        override fun insertBatch(db: Database, entries: List<MeasurementUnit>) {
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
