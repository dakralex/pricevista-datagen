package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

typealias CountryId = Int

/**
 * The [Country] entity describes a country that is represented in the
 * ISO 3166-1 standard definition.
 */
data class Country(
    /** ISO 3166-1 numeric three-digit country code **/
    val id: CountryId,

    /** ISO 3166-1 alpha-2 two-letter country code **/
    val alpha2: String,

    /** ISO 3166-1 alpha-3 three-letter country code **/
    val alpha3: String,

    /** English name of the country **/
    val name: String,
) : Entity {
    companion object : EntityComp<Country> {
        override val tableName = "Country"
        override val insertStatement = """
                insert into $tableName (id, alpha2, alpha3, name)
                values (:id, :alpha2, :alpha3, :name)
            """.trimIndent()

        override fun insertBatch(db: Database, entries: List<Country>) {
            db.updateBatch(insertStatement, entries.map { entry ->
                arrayOf(entry.id, entry.alpha2, entry.alpha3, entry.name)
            })
        }
    }

    override fun insert(db: Database) {
        db.update(insertStatement, id, alpha2, alpha3, name)
    }
}
