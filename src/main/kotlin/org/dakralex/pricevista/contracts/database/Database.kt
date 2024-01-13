package org.dakralex.pricevista.contracts.database

import java.sql.ResultSet

typealias ResultSetMapper<T> = (ResultSet) -> T

interface Database {
    fun execute(
        sql: String,
        vararg params: Any
    ): Boolean

    fun <T> query(
        sql: String,
        vararg params: Any?,
        mapper: ResultSetMapper<T>
    ): List<T>

    fun update(
        sql: String,
        vararg params: Any?
    ): Int

    fun updateBatch(
        sql: String,
        paramsList: List<Array<Any?>>
    ): IntArray

    fun isTableExistent(tableName: String): Boolean
}