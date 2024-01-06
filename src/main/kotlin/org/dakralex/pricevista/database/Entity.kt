package org.dakralex.pricevista.database

interface Entity {
    val tableName: String
    val insertStatement: String

    fun insert(db: Database)
}