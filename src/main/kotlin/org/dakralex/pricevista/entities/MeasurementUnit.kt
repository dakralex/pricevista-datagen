package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [MeasurementUnit] entity describes a measurement unit that is used by
 * stores to quantify their package and volume sizes.
 */
data class MeasurementUnit(
    val id: Int? = null,

    /** Symbol or label of the measurement unit **/
    val label: String,

    /** English name of the measurement unit in singular **/
    val singularName: String = label,

    /** English name of the measurement unit in plural **/
    val pluralName: String = singularName + "s",
) : Entity {
    companion object {
        const val tableName: String = "Measurement_Unit"
        const val insertStatement: String =
            """insert into $tableName (id, label, singular_name, plural_name) values (:id, :label, :singularName, :pluralName)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, id, label, singularName, pluralName)
    }
}
