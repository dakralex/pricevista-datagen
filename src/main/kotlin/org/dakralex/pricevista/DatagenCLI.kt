package org.dakralex.pricevista

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption
import com.github.ajalt.clikt.sources.PropertiesValueSource
import org.dakralex.pricevista.cli.DropCommand
import org.dakralex.pricevista.cli.GlobalConfig
import org.dakralex.pricevista.cli.InitCommand
import org.dakralex.pricevista.cli.UpdateCommand

const val CONFIG_FILE = "datagen.properties"

class DatagenCLI : CliktCommand(
    name = "datagen", help = """
    This is a command line tool to manage the database content of PriceVista 
    through importing content from previously retrieved JSON requests to the 
    specific store APIs.
    """.trimIndent()
) {
    init {
        versionOption(Version.getVersion())
        context {
            valueSource = PropertiesValueSource.from(CONFIG_FILE)
        }
    }

    private val verbose: Boolean by option("-v", "--verbose")
        .help("Enables verbose mode")
        .flag()

    override fun run() {
        val config =
            GlobalConfig(verbose)

        currentContext.obj = config
    }
}

fun main(args: Array<String>) =
    DatagenCLI().subcommands(InitCommand(), UpdateCommand(), DropCommand())
        .main(args)
