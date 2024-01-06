package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

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
    override val tableName = "Language"
    override val insertStatement = """
        insert into $tableName (id, alpha2, alpha3, name)
        values (:id, :alpha2, :alpha3, :name)
    """.trimIndent()

    override fun insert(db: Database) {
        db.update(insertStatement, id, alpha2, alpha3, name)
    }
}
