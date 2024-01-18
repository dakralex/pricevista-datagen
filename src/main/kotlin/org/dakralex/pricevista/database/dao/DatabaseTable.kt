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
import java.sql.SQLException

private val logger = KotlinLogging.logger {}

sealed class DatabaseTable<E : Entity, N : Any>(
    val db: Database,
    private val tableName: String,
    attributes: Sequence<String>
) : EntityDao<E, N> {
    protected open val selectAllStmt: String =
        SelectStatement(tableName, attributes).toString()
    protected open val insertFullStmt: String =
        InsertStatement(tableName, attributes).toString()

    private val ddlPath =
        "migrations/v1/create/create_${tableName.lowercase()}.sql"
    private val dropPath =
        "migrations/v1/drop/drop_${tableName.lowercase()}.sql"

    protected val isNotExistent = !db.isTableExistent(tableName)

    protected val cleanEntries = mutableSetOf<E>()
    protected val newEntries = mutableSetOf<E>()

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

    protected abstract fun matchesWithId(id: N): (E) -> Boolean

    protected abstract fun mapFromResultSet(resultSet: ResultSet): E

    protected abstract fun mapToPropArray(entry: E): Array<Any?>

    override fun add(entity: E): Boolean {
        return newEntries.add(entity)
    }

    override fun addBatch(entities: Sequence<E>) {
        newEntries.addAll(entities)
    }

    override fun findById(id: N): E? {
        val entityWithId = matchesWithId(id)

        return newEntries.find(entityWithId)
            ?: cleanEntries.find(entityWithId)
    }

    override fun commit(): EntityDao<E, N> {
        val commitEntries = newEntries.subtract(cleanEntries)
        val commitCount = commitEntries.size

        if (commitCount == 0) {
            return this
        }

        val propArrays = commitEntries.map(::mapToPropArray)

        logger.info { "$tableName: Committing $commitCount entries..." }

        val updateCount = db.updateBatch(insertFullStmt, propArrays).sum()

        // TODO Improve inefficient memory management
        cleanEntries.clear()
        newEntries.clear()
        initializeWithTableEntries()

        if (updateCount != commitCount) {
            logger.warn { "$tableName: Committed $commitCount entries, but only $updateCount rows were updated." }
        }

        return this
    }

    override fun list(): Sequence<E> {
        return sequence {
            yieldAll(cleanEntries)
            yieldAll(newEntries)
        }
    }

    protected open fun createTable(): Boolean {
        return if (db.isTableExistent(tableName)) {
            logger.warn { "$tableName is already existent, skip create." }
            false
        } else {
            runStatement("create", ddlPath, "table")
            true
        }
    }

    open fun dropTable(): Boolean {
        return if (db.isTableExistent(tableName)) {
            runStatement("drop", dropPath, "table")
            true
        } else {
            logger.warn { "$tableName does not exist existent, skip drop." }
            false
        }
    }

    private fun runStatement(
        action: String,
        path: String,
        type: String,
        name: String = tableName
    ): Boolean {
        val stmt = this::class.java.classLoader
            .getResourceAsStream(path)?.use { stream ->
                stream.bufferedReader().use(BufferedReader::readText)
            }

        if (stmt == null) {
            throw FileNotFoundException("SQL script could not be found for $type $name.")
        }

        val actionVerb = action.replaceFirstChar(Char::uppercase)
        logger.info { "$actionVerb $name $type..." }

        try {
            // Should be false, as the CREATE/DROP clause should not result in anything
            return !db.execute(stmt)
        } catch (e: SQLException) {
            logger.warn { "Could not $action $name $type." }
            return false
        }
    }
}

