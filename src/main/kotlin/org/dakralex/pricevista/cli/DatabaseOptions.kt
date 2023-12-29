package org.dakralex.pricevista.cli

import com.github.ajalt.clikt.parameters.groups.OptionGroup
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.int

class DatabaseOptions : OptionGroup(name = "Database options") {
    val dbHost by option(valueSourceKey = "db.host")
        .help("Changes database host")
        .prompt("Host")

    val dbPort by option(valueSourceKey = "db.port")
        .help("Changes database port")
        .int()
        .default(1521)

    val dbName by option(valueSourceKey = "db.name")
        .help("Changes database name")
        .default("orclcdb")

    val dbUser by option(valueSourceKey = "db.user")
        .help("Changes database username")
        .prompt("Username")

    val dbPass by option(valueSourceKey = "db.pass")
        .help("Changes database password")
        .prompt("Password")
}