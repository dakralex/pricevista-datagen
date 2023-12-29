package org.dakralex.pricevista.database

abstract class Table(val tableName: String) {
    abstract fun create()
    abstract fun update()
    abstract fun delete()
}
