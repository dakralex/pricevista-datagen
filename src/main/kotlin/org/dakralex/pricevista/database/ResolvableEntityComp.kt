package org.dakralex.pricevista.database

interface ResolvableEntityComp<T : Entity> : EntityComp<T> {
    val selectStatement: String
}
