package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.groups.provideDelegate
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.split
import com.github.ajalt.clikt.parameters.types.enum
import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.entities.data.EStore

class InitCommand(name: String = "init") :
    CliktCommand(name = name, help = "Initialize the database") {

    private val dbOpts by DatabaseOptions()

    private val migrationTarget by option()
        .help("Sets the target version of the database migrations")
        .default("v1")

    /**
     * The stores which should be initialized on the database
     */
    val stores by option()
        .help("Sets the stores that should be considered")
        .enum<EStore>()
        .split(",")
        .default(EStore.entries)

    private fun createTable(db: Database, tableName: String) {
        db.executeMigrationScript(migrationTarget, "create", tableName)
    }

    override fun run() {
        val db = Database.connect(
            dbOpts.dbHost,
            dbOpts.dbPort,
            dbOpts.dbName,
            dbOpts.dbUser,
            dbOpts.dbPass
        )

        createTable(db, "Country")
        createTable(db, "Currency")
        createTable(db, "Language")
        createTable(db, "Measurement_Unit")
        createTable(db, "Place")
        createTable(db, "Company")
        createTable(db, "Company_Participation")
        createTable(db, "Retailer")
        createTable(db, "Brand")
        createTable(db, "Category")
        createTable(db, "Article")
        createTable(db, "Article_Category")
        createTable(db, "Article_Image")
        createTable(db, "Store")
        createTable(db, "Store_Article_Map")
        createTable(db, "Store_Category_Map")
        createTable(db, "Current_Price")
        createTable(db, "Recorded_Price")
    }
}
