package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.split
import com.github.ajalt.clikt.parameters.types.enum
import org.dakralex.pricevista.shops.RegisteredRetailers

class InitCommand(name: String = "init") :
    CliktCommand(name = name, help = "Initialize the database") {

    /**
     * The retailers which should be initialized on the database
     */
    val retailers by option()
        .help("Sets the online retailers that should be considered")
        .enum<RegisteredRetailers>()
        .split(",")
        .default(listOf(RegisteredRetailers.BILLA))

    override fun run() {
        TODO("Not yet implemented")
    }
}
