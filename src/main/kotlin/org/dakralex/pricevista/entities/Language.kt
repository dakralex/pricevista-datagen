package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

typealias LanguageId = Int

/**
 * The [Language] entity describes a language that is represented in the
 * ISO 639-1 standard definition.
 */
data class Language(
    /** ISO 639-1 three-digit language code **/
    val id: LanguageId,

    /** ISO 639-1 two-letter language code **/
    val alpha2: String,

    /** ISO 639-2 three-letter language code **/
    val alpha3: String,

    /** English name of the language **/
    val name: String
) : Entity {
    companion object : EntityComp<Language> {
        override val tableName: String = "Language"
        override val insertStatement: String = """
                insert into $tableName (id, alpha2, alpha3, name)
                values (:id, :alpha2, :alpha3, :name)
            """.trimIndent()

        override fun insertBatch(db: Database, entries: List<Language>) {
            db.updateBatch(insertStatement, entries.map { entry ->
                arrayOf(entry.id, entry.alpha2, entry.alpha3, entry.name)
            })
        }
    }

    override fun insert(db: Database) {
        db.update(insertStatement, id, alpha2, alpha3, name)
    }
}
