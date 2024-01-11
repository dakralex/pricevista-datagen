package org.dakralex.pricevista.database

import org.dakralex.pricevista.contracts.database.Database

interface Entity {
    fun insert(db: Database)
}