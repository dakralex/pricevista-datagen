package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [Country] entity describes a country that is represented in the
 * ISO 3166-1 standard definition.
 */
data class Country(
    /** ISO 3166-1 numeric three-digit country code **/
    val id: Int,

    /** ISO 3166-1 alpha-2 two-letter country code **/
    val alpha2: String,

    /** ISO 3166-1 alpha-3 three-letter country code **/
    val alpha3: String,

    /** English name of the country **/
    val name: String,
) : Entity {
    override val tableName = "Country"
    override val insertStatement = """
        insert into $tableName (id, alpha2, alpha3, name)
        values (:id, :alpha2, :alpha3, :name)
    """.trimIndent()

    override fun insert(db: Database) {
        db.update(insertStatement, id, alpha2, alpha3, name)
    }
}
