package org.dakralex.pricevista.database

abstract class Table<T : Tuple>(val tableName: String) {
    abstract fun create()
    abstract fun update()
    abstract fun delete()
}

