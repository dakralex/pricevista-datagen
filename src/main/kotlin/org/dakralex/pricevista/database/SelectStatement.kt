package org.dakralex.pricevista.database

import org.dakralex.pricevista.contracts.database.SqlStatement

class SelectStatement(
    private val tableName: String,
    private val attributes: List<String>
) : SqlStatement {
    override fun toString(): String {
        val assignments = attributes.joinToString(", ")

        return """select $assignments from $tableName"""
    }
}