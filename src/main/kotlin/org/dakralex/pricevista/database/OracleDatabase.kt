package org.dakralex.pricevista.database

import io.github.oshai.kotlinlogging.KotlinLogging
import oracle.jdbc.driver.OracleDriver
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.contracts.database.Mapper
import java.io.BufferedReader
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
        return conn.prepareStatement(sql).use { statement ->
            params.withIndex().forEach { (index, param) ->
                statement.setObject(index + 1, param)
            }

            statement.execute()
        }
    }

    override fun executeMigration(
        action: String,
        entityName: String,
        target: String
    ): Boolean {
        val actionName = action.lowercase()

        val path =
            "migrations/${target}/${actionName}/${actionName}_${entityName.lowercase()}.sql"
        val script = this::class.java.classLoader
            .getResourceAsStream(path)?.use { stream ->
                stream.bufferedReader().use(BufferedReader::readText)
            }

        if (script == null) {
            logger.warn { "Script for entity $entityName not found, skip $actionName." }

            return false
        }

        when (actionName) {
            "create" -> {
                if (isTableExistent(entityName)) {
                    logger.info { "$entityName is already existent, skip $actionName." }

                    return false
                }
            }

            else -> {
                if (!isTableExistent(entityName)) {
                    logger.info { "$entityName is not existent, skip $actionName." }

                    return false
                }
            }
        }

        val actionVerb = actionName.replaceFirstChar(Char::uppercase)
        logger.info { "$actionVerb $entityName table..." }

        return execute(script)
    }

    override fun <T> query(
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

    override fun update(sql: String, vararg params: Any?): Int {
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

    override fun updateBatch(
        sql: String,
        paramsList: List<Array<Any?>>
    ): IntArray {
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

    private fun isTableExistent(tableName: String) =
        isObjectExistent(tableName, "table")
}

