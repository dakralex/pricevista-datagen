package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.parameters.groups.OptionGroup
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.int
import org.dakralex.pricevista.database.OracleDatabase

class DatabaseOptions : OptionGroup(name = "Database options") {
    private val dbHost by option(valueSourceKey = "db.host")
        .help("Changes database host")
        .prompt("Host")

    private val dbPort by option(valueSourceKey = "db.port")
        .help("Changes database port")
        .int()
        .default(1521)

    private val dbName by option(valueSourceKey = "db.name")
        .help("Changes database name")
        .default("orclcdb")

    private val dbUser by option(valueSourceKey = "db.user")
        .help("Changes database username")
        .prompt("Username")

    private val dbPass by option(valueSourceKey = "db.pass")
        .help("Changes database password")
        .prompt("Password")

    fun toOracleDatabase(): OracleDatabase {
        return OracleDatabase.connect(dbHost, dbPort, dbName, dbUser, dbPass)
    }
}