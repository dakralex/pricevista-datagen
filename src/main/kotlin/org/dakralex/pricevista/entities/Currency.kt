package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

typealias CurrencyId = Int

/**
 * The [Currency] entity describes a currency that is represented in the
 * ISO 4217 standard definition.
 */
data class Currency(
    /** ISO 4217 three-digit currency code **/
    val id: CurrencyId,

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
    companion object : EntityComp<Currency> {
        override val tableName: String = "Currency"
        override val insertStatement: String = """
                insert into $tableName (id, alpha3, scale, symbol, minor, name)
                values (:id, :alpha3, :scale, :symbol, :minor, :name)
            """.trimIndent()

        override fun insertBatch(db: Database, entries: List<Currency>) {
            db.updateBatch(insertStatement, entries.map { entry ->
                arrayOf(
                    entry.id,
                    entry.alpha3,
                    entry.scale,
                    entry.symbol,
                    entry.minor,
                    entry.name
                )
            })
        }
    }

    override fun insert(db: Database) {
        db.update(insertStatement, id, alpha3, scale, symbol, minor, name)
    }
}
