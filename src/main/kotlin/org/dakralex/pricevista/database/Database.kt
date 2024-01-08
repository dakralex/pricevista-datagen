package org.dakralex.pricevista.database

import io.github.oshai.kotlinlogging.KotlinLogging
import oracle.jdbc.driver.OracleDriver
import java.io.BufferedReader
import java.sql.*

private val logger = KotlinLogging.logger {}

class Database(private val conn: Connection) {
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
        ): Database {
            val url = "jdbc:oracle:thin:@$host:$port:$name"

            try {
                val conn = DriverManager.getConnection(
                    url,
                    user,
                    password
                )

                logger.info { "Connected to the database $url successfully." }

                return Database(conn)
            } catch (e: SQLException) {
                logger.error { "Could not connect to the database $url: $e" }

                throw e
            }
        }
    }

    fun execute(sql: String, vararg params: Any): Boolean {
        return conn.prepareStatement(sql).use { statement ->
            params.withIndex().forEach { (index, param) ->
                statement.setObject(index + 1, param)
            }

            statement.execute()
        }
    }

    fun <T> query(
        sql: String,
        vararg params: Any?,
        mapper: Mapper<T>
    ): List<T> {
        return conn.prepareStatement(sql).use { statement ->
            params.withIndex().forEach { (index, param) ->
                if (param == null) {
                    statement.setObject(index + 1, Types.NULL)
                } else {
                    statement.setObject(index + 1, param)
                }
            }

            statement.executeQuery().use { set ->
                val result = mutableListOf<T>()

                while (set.next()) {
                    result.add(mapper(set))
                }

                result
            }
        }
    }

    fun update(sql: String, vararg params: Any?): Int {
        return conn.prepareStatement(sql).use { statement ->
            params.withIndex().forEach { (index, param) ->
                if (param == null) {
                    statement.setObject(index + 1, Types.NULL)
                } else {
                    statement.setObject(index + 1, param)
                }
            }

            statement.executeUpdate()
        }
    }

    fun updateBatch(sql: String, paramsList: List<Array<Any?>>): IntArray {
        return conn.prepareStatement(sql).use { statement ->
            paramsList.forEach { params ->
                params.withIndex().forEach { (index, param) ->
                    if (param == null) {
                        statement.setObject(index + 1, Types.NULL)
                    } else {
                        statement.setObject(index + 1, param)
                    }
                }

                statement.addBatch()
            }

            statement.executeBatch()
        }
    }

    private fun isObjectExistent(
        objectName: String,
        type: String
    ): Boolean {
        val sql = """
            select count(*) as COUNT
            from ALL_OBJECTS
            where OBJECT_TYPE in ('${type.uppercase()}')
            and OBJECT_NAME = '${objectName.uppercase()}'
        """.trimIndent()

        val res = query(sql) {
            it.getInt("COUNT") > 0
        }

        return res.first()
    }

    fun isTableExistent(tableName: String) =
        isObjectExistent(tableName, "table")

    fun isViewExistent(viewName: String) =
        isObjectExistent(viewName, "view")

    fun executeMigrationScript(
        migrationTarget: String,
        action: String,
        entityName: String
    ) {
        val actionName = action.lowercase()

        val path =
            "migrations/${migrationTarget}/${actionName}/${actionName}_${entityName.lowercase()}.sql"
        val script = this::class.java.classLoader
            .getResourceAsStream(path)?.use { stream ->
                stream.bufferedReader().use(BufferedReader::readText)
            }

        if (script == null) {
            return logger.warn { "Script for entity $entityName not found, skip $actionName." }
        }

        when (actionName) {
            "create" -> {
                if (isTableExistent(entityName)) {
                    return logger.info { "$entityName is already existent, skip $actionName." }
                }
            }

            else -> {
                if (!isTableExistent(entityName)) {
                    return logger.info { "$entityName is not existent, skip $actionName." }
                }
            }
        }

        val actionVerb = actionName.replaceFirstChar(Char::uppercase)
        logger.info { "$actionVerb $entityName table..." }

        execute(script)
    }
}

typealias Mapper<T> = (ResultSet) -> T
