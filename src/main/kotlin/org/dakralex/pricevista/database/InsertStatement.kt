package org.dakralex.pricevista.database

import org.dakralex.pricevista.contracts.database.SqlStatement

class InsertStatement(
    private val tableName: String,
    private val attributes: Sequence<String>
) : SqlStatement {
    override fun toString(): String {
        val assignments = attributes.joinToString(", ")
        val placeholders = attributes.map { it.replace("_", "") }
            .joinToString(", ") { ":$it" }

        return """insert into $tableName ($assignments) values ($placeholders)"""
    }
}