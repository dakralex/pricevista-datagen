package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.ResolvableEntity
import org.dakralex.pricevista.database.ResolvableEntityComp

typealias CompanyId = Int

/**
 * The [Company] entity is the generalization of the different companies that
 * are somewhere in the supply chain. In this micro-world, they are retailers
 * and brands.
 */
data class Company(
    var id: CompanyId? = null,

    /** Full name of the company **/
    var longName: String? = null,

    /** Short name of the company **/
    var shortName: String,

    /** Physical location of the company's headquarters **/
    var place: Place? = null
) : ResolvableEntity {
    companion object : ResolvableEntityComp<Company> {
        override val tableName: String = "Company"
        override val insertStatement: String = """
                insert into $tableName (id, long_name, short_name, place_id)
                values (:id, :longName, :shortName, :placeId)
            """.trimIndent()
        val insertNewStatement: String = """
                insert into $tableName (long_name, short_name)
                values (:longName, :shortName)
            """.trimIndent()
        override val selectStatement: String = """
                select id from $tableName
                    where short_name = :shortName
            """.trimIndent()

        override fun insertBatch(db: Database, entries: List<Company>) {
            entries.forEach { it.resolveFrom(db) }

            db.updateBatch(insertStatement, entries.map { entry ->
                arrayOf(
                    entry.id,
                    entry.longName,
                    entry.shortName,
                    entry.place?.id
                )
            })
        }
    }

    override fun resolveFrom(db: Database) {
        id = db.query(
            selectStatement,
            shortName
        ) {
            it.getInt("id")
        }.firstOrNull() ?: id
    }

    override fun insert(db: Database) {
        if (id == null) {
            resolveFrom(db)
        }

        if (id == null) {
            db.update(insertNewStatement, longName, shortName)
        } else {
            db.update(insertStatement, id, longName, shortName, place?.id)
        }
    }

    override fun toString(): String {
        return "Company(id=$id, longName=$longName, shortName='$shortName', place=$place)"
    }
}
