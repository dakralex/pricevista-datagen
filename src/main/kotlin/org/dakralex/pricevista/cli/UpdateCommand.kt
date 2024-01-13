package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.groups.provideDelegate
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.file
import org.dakralex.pricevista.database.OracleDatabase
import org.dakralex.pricevista.entities.data.EStore
import org.dakralex.pricevista.parser.billa.BillaJsonParser
import org.dakralex.pricevista.parser.hofer.HoferJsonParser
import org.dakralex.pricevista.parser.spar.SparJsonParser

class UpdateCommand(name: String = "update") :
    CliktCommand(name = name, help = "Update the database") {

    private val dbOpts by DatabaseOptions()

    /**
     * The targeted store for this update command
     */
    private val store by argument().enum<EStore>().default(EStore.BILLA)

    /**
     * The files that should be considered for this update
     */
    private val files by argument()
        .file(mustExist = true, mustBeReadable = true)
        .multiple()

    override fun run() {
        val db = dbOpts.toOracleDatabase()
        val repo = PriceVistaDatabase(db).init()

        val parser = when (store) {
            EStore.BILLA -> BillaJsonParser
            EStore.HOFER -> HoferJsonParser
            EStore.SPAR -> SparJsonParser
        }

        parser.parseEntries(files)
    }
}