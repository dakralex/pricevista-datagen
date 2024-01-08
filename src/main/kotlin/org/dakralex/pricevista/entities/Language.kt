package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [Language] entity describes a language that is represented in the
 * ISO 639-1 standard definition.
 */
data class Language(
    /** ISO 639-1 three-digit language code **/
    val id: Int,

    /** ISO 639-1 two-letter language code **/
    val alpha2: String,

    /** ISO 639-2 three-letter language code **/
    val alpha3: String,

    /** English name of the language **/
    val name: String
) : Entity {
    companion object {
        const val tableName: String = "Language"
        const val insertStatement: String =
            """insert into $tableName (id, alpha2, alpha3, name) values (:id, :alpha2, :alpha3, :name)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, id, alpha2, alpha3, name)
    }
}
