package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.file
import org.dakralex.pricevista.parser.billa.BillaJsonParser
import org.dakralex.pricevista.parser.hofer.HoferJsonParser
import org.dakralex.pricevista.parser.spar.SparJsonParser
import org.dakralex.pricevista.shops.RegisteredRetailers

class UpdateCommand(name: String = "update") :
    CliktCommand(name = name, help = "Update the database") {

    /**
     * The targeted retailers for this update command
     */
    private val retailer by argument().enum<RegisteredRetailers>()

    /**
     * The files that should be considered for this update
     */
    private val files by argument()
        .file(mustExist = true, mustBeReadable = true)
        .multiple()

    override fun run() {
        when (retailer) {
            RegisteredRetailers.BILLA -> BillaJsonParser.parseEntries(files)
            RegisteredRetailers.HOFER -> HoferJsonParser.parseEntries(files)
            RegisteredRetailers.SPAR -> SparJsonParser.parseEntries(files)
            else -> TODO()
        }
    }
}