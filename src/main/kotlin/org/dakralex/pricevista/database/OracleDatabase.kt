package org.dakralex.pricevista.database

import io.github.oshai.kotlinlogging.KotlinLogging
import oracle.jdbc.driver.OracleDriver
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.contracts.database.ResultSetMapper
import java.sql.*

private val logger = KotlinLogging.logger {}

class OracleDatabase(private val conn: Connection) : Database {
    companion object {
        init {
            DriverManager.registerDriver(OracleDriver())
        }

        @Throws(SQLException::class)
        fun connect(
            host: String,
            port: Number?,
            name: String?,
            user: String,
            password: String
        ): OracleDatabase {
            val url = "jdbc:oracle:thin:@$host:$port:$name"

            try {
                val conn = DriverManager.getConnection(
                    url,
                    user,
                    password
                )

                logger.info { "Connected to the database $url successfully." }

                return OracleDatabase(conn)
            } catch (e: SQLException) {
                logger.error { "Could not connect to the database $url: $e" }

                throw e
            }
        }
    }

    override fun execute(sql: String, vararg params: Any): Boolean {
        return conn.prepareStatement(sql).use { ps ->
            params.withIndex().forEach { (i, param) ->
                val index = i + 1

                ps.setObject(index, param)
            }

            ps.execute()
        }
    }

    override fun <T> query(
        sql: String,
        vararg params: Any?,
        mapper: ResultSetMapper<T>
    ): List<T> {
        return conn.prepareStatement(sql).use { ps ->
            setPsParams(ps, params)

            ps.executeQuery().use { s ->
                val result = mutableListOf<T>()

                while (s.next()) {
                    result.add(mapper(s))
                }

                result
            }
        }
    }

    override fun update(sql: String, vararg params: Any?): Int {
        return conn.prepareStatement(sql).use { ps ->
            setPsParams(ps, params)

            ps.executeUpdate()
        }
    }

    override fun updateBatch(
        sql: String,
        paramsList: List<Array<Any?>>
    ): IntArray {
        return conn.prepareStatement(sql).use { ps ->
            paramsList.forEach { params ->
                setPsParams(ps, params)

                ps.addBatch()
            }

            try {
                ps.executeBatch()
            } catch (e: BatchUpdateException) {
                val updateCount = e.updateCounts.sum()
                val probableItem = paramsList[updateCount].joinToString(", ")

                logger.error { "Could not fully execute batch update (error at update number: $updateCount)" }
                logger.error { "Probable erroneous item values: ($probableItem)" }
                throw e
            }
        }
    }

    private fun setPsParams(
        ps: PreparedStatement,
        params: Array<out Any?>
    ) {
        params.withIndex().forEach { (i, param) ->
            val index = i + 1

            try {
                when (param) {
                    is String -> ps.setString(index, param)
                    is Int -> ps.setInt(index, param)
                    is Double -> ps.setDouble(index, param)
                    is Boolean -> ps.setBoolean(index, param)
                    else -> ps.setObject(index, param)
                }
            } catch (e: SQLException) {
                logger.error { "Could not set object (index: $index, value: $param)" }
                throw e
            }
        }
    }

    private fun isObjectExistent(
        type: String,
        name: String
    ): Boolean {
        val sql = """
            select count(*) as COUNT
            from ALL_OBJECTS
            where OBJECT_TYPE in ('${type.uppercase()}')
            and OBJECT_NAME = '${name.uppercase()}'
        """.trimIndent()

        val res = query(sql) {
            it.getInt("COUNT") > 0
        }

        return res.first()
    }

    override fun isTableExistent(tableName: String): Boolean =
        isObjectExistent("table", tableName)
}

