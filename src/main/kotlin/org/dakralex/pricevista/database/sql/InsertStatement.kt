package org.dakralex.pricevista.database.sql

import org.dakralex.pricevista.contracts.database.SqlStatement

class InsertStatement(
    private val tableName: String,
    private val attributes: Sequence<String>
) : SqlStatement {
    override fun toString(): String {
        val assignments = SqlStatement.formatAttributes(attributes)
        val placeholders = SqlStatement.formatPlaceholders(attributes)

        return """insert into $tableName ($assignments) values ($placeholders)"""
    }
}