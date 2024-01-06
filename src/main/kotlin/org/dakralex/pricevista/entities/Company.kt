package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [Company] entity is the generalization of the different companies that
 * are somewhere in the supply chain. In this micro-world, they are retailers
 * and brands.
 */
data class Company(
    val id: Int? = null,

    /** Full name of the company **/
    var longName: String? = null,

    /** Short name of the company **/
    var shortName: String,

    /** Physical location of the company's headquarters **/
    var place: Place? = null
) : Entity {
    override val tableName = "Company"
    override val insertStatement = """
        insert into $tableName (id, long_name, short_name, place_id)
        values (:id, :longName, :shortName, :placeId)
    """.trimIndent()

    override fun insert(db: Database) {
        db.update(insertStatement, id, longName, shortName, place?.id)
    }
}
