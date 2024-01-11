package org.dakralex.pricevista.database

import org.dakralex.pricevista.contracts.database.Database

interface ResolvableEntity : Entity {
    fun resolveFrom(db: Database)
}
