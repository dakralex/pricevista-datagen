package org.dakralex.pricevista.database

interface ResolvableEntity : Entity {
    fun resolveFrom(db: Database)
}
