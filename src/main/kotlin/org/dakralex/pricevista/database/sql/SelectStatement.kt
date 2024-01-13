package org.dakralex.pricevista.database.sql

import org.dakralex.pricevista.contracts.database.SqlStatement

class SelectStatement(
    private val tableName: String,
    private val attributes: Sequence<String>
) : SqlStatement {
    override fun toString(): String {
        val assignments = SqlStatement.formatAttributes(attributes)

        return """select $assignments from $tableName"""
    }
}