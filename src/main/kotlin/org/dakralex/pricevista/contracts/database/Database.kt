package org.dakralex.pricevista.contracts.database

import java.sql.ResultSet

typealias Mapper<T> = (ResultSet) -> T

interface Database {
    fun execute(sql: String, vararg params: Any): Boolean

    fun executeMigration(action: String, entityName: String, target: String): Boolean

    fun <T> query(sql: String, vararg params: Any?, mapper: Mapper<T>): List<T>

    fun update(sql: String, vararg params: Any?): Int

    fun updateBatch(sql: String, paramsList: List<Array<Any?>>): IntArray
}