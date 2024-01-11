package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.groups.provideDelegate
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.split
import com.github.ajalt.clikt.parameters.types.enum
import io.github.oshai.kotlinlogging.KotlinLogging
import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.entities.*
import org.dakralex.pricevista.entities.data.*

private val logger = KotlinLogging.logger {}

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
        logger.info { "Inserting preliminary country data..." }
        Country.insertBatch(db, ECountry.entries.map(ECountry::country))

        createTable(db, "Currency")
        logger.info { "Inserting preliminary currency data..." }
        Currency.insertBatch(db, ECurrency.entries.map(ECurrency::currency))

        createTable(db, "Language")
        logger.info { "Inserting preliminary language data..." }
        Language.insertBatch(db, ELanguage.entries.map(ELanguage::language))

        createTable(db, "Measurement_Unit")
        logger.info { "Inserting preliminary measurement units data..." }
        MeasurementUnit.insertBatch(
            db,
            EMeasurementUnit.entries.map(EMeasurementUnit::unit)
        )

        createTable(db, "Place")
        logger.info { "Inserting preliminary place data..." }
        Place.insertBatch(db, EPlace.entries.map(EPlace::place))

        createTable(db, "Company")
        logger.info { "Inserting preliminary company data..." }
        Company.insertBatch(db, ECompany.entries.map(ECompany::company))

        createTable(db, "Company_Participation")

        createTable(db, "Retailer")
        logger.info { "Inserting preliminary retailer data..." }
        Retailer.insertBatch(db, ERetailer.entries.map(ERetailer::retailer))

        createTable(db, "Brand")
        createTable(db, "Category")
        createTable(db, "Article")
        createTable(db, "Article_Category")
        createTable(db, "Article_Image")

        createTable(db, "Store")
        logger.info { "Inserting preliminary store data..." }
        Store.insertBatch(db, EStore.entries.map(EStore::store))

        createTable(db, "Store_Article")
        createTable(db, "Store_Category")
        createTable(db, "Current_Price")
        createTable(db, "Recorded_Price")

        logger.info { "PriceVista database $migrationTarget initialized successfully." }
    }
}
