package org.dakralex.pricevista.database.dao

import io.github.oshai.kotlinlogging.KotlinLogging
import org.dakralex.pricevista.contracts.dao.EntityDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.contracts.entities.Entity
import org.dakralex.pricevista.database.sql.InsertStatement
import org.dakralex.pricevista.database.sql.SelectStatement
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.sql.ResultSet

private val logger = KotlinLogging.logger {}

sealed class DatabaseTable<E : Entity, N : Any>(
    val db: Database,
    private val tableName: String,
    attributes: Sequence<String>
) : EntityDao<E, N> {
    private val selectAllStmt: String =
        SelectStatement(tableName, attributes).toString()
    private val insertFullStmt: String =
        InsertStatement(tableName, attributes).toString()

    private val ddlPath =
        "migrations/v1/create/create_${tableName.lowercase()}.sql"
    private val dropPath =
        "migrations/v1/drop/drop_${tableName.lowercase()}.sql"

    private val isNotExistent = !db.isTableExistent(tableName)

    private val cleanEntries = mutableSetOf<E>()
    private val newEntries = mutableSetOf<E>()

    private fun initializeWithTableEntries() {
        cleanEntries += db.query(selectAllStmt, mapper = ::mapFromResultSet)
    }

    open fun initialize(): Boolean {
        if (isNotExistent) {
            return createTable()
        }

        initializeWithTableEntries()
        return false
    }

    protected abstract fun isUnique(entity: E): (E) -> Boolean

    protected abstract fun matchesWithId(id: N): (E) -> Boolean

    protected abstract fun mapFromResultSet(resultSet: ResultSet): E

    protected abstract fun mapToPropArray(entry: E): Array<Any?>

    override fun add(entity: E): Boolean {
        val isEntityUnique = isUnique(entity)

        return cleanEntries.any(isEntityUnique)
                || newEntries.any(isEntityUnique)
                || newEntries.add(entity)
    }

    final override fun addBatch(entities: Sequence<E>) {
        entities.forEach(::add)
    }

    override fun findById(id: N): E? {
        val entityWithId = matchesWithId(id)

        return cleanEntries.find(entityWithId)
            ?: newEntries.find(entityWithId)
    }

    override fun commit(): EntityDao<E, N> {
        val newCount = newEntries.size

        if (newCount == 0) {
            return this
        }

        val propArrays = newEntries.map(::mapToPropArray)

        logger.info { "$tableName: Committing $newCount entries..." }

        val updateCount = db.updateBatch(insertFullStmt, propArrays).sum()

        // TODO Improve inefficient memory management
        cleanEntries.clear()
        newEntries.clear()
        initializeWithTableEntries()

        if (updateCount != newCount) {
            logger.warn { "$tableName: Committed $newCount entries, but only $updateCount rows were updated." }
        }

        return this
    }

    override fun list(): Sequence<E> {
        return sequence {
            yieldAll(cleanEntries)
            yieldAll(newEntries)
        }
    }

    private fun createTable(): Boolean {
        return runStatement("create", ddlPath)
    }

    fun dropTable(): Boolean {
        return runStatement("drop", dropPath)
    }

    private fun runStatement(action: String, path: String): Boolean {
        val stmt = this::class.java.classLoader
            .getResourceAsStream(path)?.use { stream ->
                stream.bufferedReader().use(BufferedReader::readText)
            }

        if (stmt == null) {
            throw FileNotFoundException("SQL script could not be found for table $tableName.")
        }

        when (action) {
            "create" -> {
                if (db.isTableExistent(tableName)) {
                    logger.warn { "$tableName is already existent, skip $action." }
                    return false
                }
            }

            else -> {
                if (!db.isTableExistent(tableName)) {
                    logger.warn { "$tableName is not existent, skip $action." }
                    return false
                }
            }
        }

        val actionVerb = action.replaceFirstChar(Char::uppercase)
        logger.info { "$actionVerb $tableName table..." }

        // Should be false, as the CREATE clause should not result in anything
        return !db.execute(stmt)
    }
}

