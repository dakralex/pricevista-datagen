package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [MeasurementUnit] entity describes a measurement unit that is used by
 * stores to quantify their package and volume sizes.
 */
data class MeasurementUnit(
    val id: Int? = null,

    /** Symbol of the measurement unit **/
    val symbol: String,

    /** English name of the measurement unit **/
    val name: String,
) : Entity {
    override val tableName = "Measurement_Unit"
    override val insertStatement = """
        insert into $tableName (id, symbol, name)
        values (:id, :symbol, :name)
    """.trimIndent()

    override fun insert(db: Database) {
        db.update(insertStatement, id, symbol, name)
    }
}
