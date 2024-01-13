package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.groups.provideDelegate
import io.github.oshai.kotlinlogging.KotlinLogging
import org.dakralex.pricevista.database.PriceVistaDatabase

private val logger = KotlinLogging.logger {}

class DropCommand(name: String = "drop") :
    CliktCommand(name = name, help = "Drop the database") {
    private val dbOpts by DatabaseOptions()

    override fun run() {
        val db = dbOpts.toOracleDatabase()
        PriceVistaDatabase(db).drop()
        logger.info { "PriceVista database dropped successfully." }
    }
}
