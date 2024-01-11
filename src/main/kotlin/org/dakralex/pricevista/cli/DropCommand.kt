package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.groups.provideDelegate
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import org.dakralex.pricevista.database.Database

class DropCommand(name: String = "drop") :
    CliktCommand(name = name, help = "Drop the database") {
    private val dbOpts by DatabaseOptions()

    private val migrationTarget by option()
        .help("Sets the target version of the database migrations")
        .default("v1")

    private fun dropTable(db: Database, tableName: String) {
        db.executeMigrationScript(migrationTarget, "drop", tableName)
    }

    override fun run() {
        val db = Database.connect(
            dbOpts.dbHost,
            dbOpts.dbPort,
            dbOpts.dbName,
            dbOpts.dbUser,
            dbOpts.dbPass
        )

        dropTable(db, "Recorded_Price")
        dropTable(db, "Current_Price")
        dropTable(db, "Store_Category")
        dropTable(db, "Store_Article")
        dropTable(db, "Store")
        dropTable(db, "Article_Image")
        dropTable(db, "Article_Category")
        dropTable(db, "Article")
        dropTable(db, "Category")
        dropTable(db, "Brand")
        dropTable(db, "Retailer")
        dropTable(db, "Company_Participation")
        dropTable(db, "Company")
        dropTable(db, "Place")
        dropTable(db, "Measurement_Unit")
        dropTable(db, "Language")
        dropTable(db, "Currency")
        dropTable(db, "Country")
    }
}
