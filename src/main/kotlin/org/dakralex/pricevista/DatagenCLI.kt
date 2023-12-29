package org.dakralex.pricevista

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.groups.OptionGroup
import com.github.ajalt.clikt.parameters.groups.provideDelegate
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.sources.PropertiesValueSource
import org.dakralex.pricevista.shops.RegisteredRetailers

const val CONFIG_FILE = "datagen.properties"

data class Config(var databaseUri: String, var databaseUser: String, var databasePass: String, var verbose: Boolean)

class DatabaseOptions : OptionGroup(name = "Database options") {
    val host by option("--db-host", valueSourceKey = "db.host", help = "Changes database host").prompt("Host")
    val port by option("--db-port", valueSourceKey = "db.port", help = "Changes database port").int().default(1521)
    val name by option("--db-name", valueSourceKey = "db.name", help = "Changes database name").default("orclcdb")
    val user by option("--db-user", valueSourceKey = "db.user", help = "Changes database username").prompt("Username")
    val pass by option("--db-pass", valueSourceKey = "db.pass", help = "Changes database password").prompt("Password")
}

class DatagenCLI : CliktCommand(
    name = "datagen", help = """
    This is a command line tool to manage the database content of PriceVista 
    through importing content from previously retrieved JSON requests to the 
    specific store APIs.
    """.trimIndent()
) {
    init {
        versionOption(version())
        context {
            valueSource = PropertiesValueSource.from(CONFIG_FILE)
        }
    }

    private val database by DatabaseOptions()
    private val verbose: Boolean by option(
        "-v",
        "--verbose",
        help = "Enables verbose mode"
    ).flag()

    override fun run() {
        val config = Config(database.host, database.user, database.pass, verbose)

        currentContext.obj = config
    }
}

class Init : CliktCommand(help = "Initialize the database") {
    val retailers by option(help = "Sets the online retailers that should be considered").enum<RegisteredRetailers>(true)
        .split(",")

    override fun run() {
        TODO("Not yet implemented")
    }
}

class Update : CliktCommand(help = "Update the database") {

    val files by argument().file(
        mustExist = true,
        mustBeReadable = true
    ).multiple()

    override fun run() {
        TODO("Not yet implemented")
    }
}

class Drop : CliktCommand(help = "Drop the database") {

    override fun run() {
        TODO("Not yet implemented")
    }
}

fun main(args: Array<String>) =
    DatagenCLI().subcommands(Init(), Update(), Drop()).main(args)

// TODO Sync with gradle version
fun version() = "1.0-SNAPSHOT"
