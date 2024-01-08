package org.dakralex.pricevista.database

interface EntityComp<T : Entity> {
    val tableName: String
    val insertStatement: String

    fun insertBatch(db: Database, entries: List<T>) {
        entries.forEach { entry -> entry.insert(db) }
    }
}
