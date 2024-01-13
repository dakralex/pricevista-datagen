package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.groups.provideDelegate
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.split
import com.github.ajalt.clikt.parameters.types.enum
import io.github.oshai.kotlinlogging.KotlinLogging
import org.dakralex.pricevista.database.OracleDatabase
import org.dakralex.pricevista.database.PriceVistaDatabase
import org.dakralex.pricevista.entities.data.EStore

private val logger = KotlinLogging.logger {}

class InitCommand(name: String = "init") :
    CliktCommand(name = name, help = "Initialize the database") {

    private val dbOpts by DatabaseOptions()

    /**
     * The stores which should be initialized on the database
     */
    val stores by option()
        .help("Sets the stores that should be considered")
        .enum<EStore>()
        .split(",")
        .default(EStore.entries)

    override fun run() {
        val db = OracleDatabase.connect(
            dbOpts.dbHost,
            dbOpts.dbPort,
            dbOpts.dbName,
            dbOpts.dbUser,
            dbOpts.dbPass
        )

        val pvDb = PriceVistaDatabase(db)
        pvDb.initialize()
        pvDb.commit()

        logger.info { "PriceVista database initialized successfully." }
    }
}
