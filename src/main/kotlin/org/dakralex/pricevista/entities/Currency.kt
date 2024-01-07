package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [Currency] entity describes a currency that is represented in the
 * ISO 4217 standard definition.
 */
data class Currency(
    /** ISO 4217 three-digit currency code **/
    val id: Int,

    /** ISO 4217 three-letter currency code **/
    val alpha3: String,

    /** Number of digits for the subunit */
    val scale: Int,

    /** Symbol of the currency **/
    val symbol: String,

    /** Symbol of the currency's subunit */
    val minor: String? = null,

    /** English name of the currency **/
    val name: String
) : Entity {
    override val tableName = "Currency"
    override val insertStatement = """
        insert into $tableName (id, alpha3, scale, symbol, minor, name)
        values (:id, :alpha3, :scale, :symbol, :minor, :name)
    """.trimIndent()

    override fun insert(db: Database) {
        db.update(insertStatement, id, alpha3, scale, symbol, minor, name)
    }
}
