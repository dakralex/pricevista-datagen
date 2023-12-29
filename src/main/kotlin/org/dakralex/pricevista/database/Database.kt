package org.dakralex.pricevista.database

import java.sql.Connection
import java.sql.DriverManager

class Database(val conn: Connection) {
    val url: String = conn.metaData.url
    val user: String = conn.metaData.userName

    fun connect(
        host: String,
        port: Number?,
        name: String?,
        user: String,
        password: String
    ): Database {
        return Database(
            DriverManager.getConnection(
                "jdbc:oracle:thin:@$host:$port:$name",
                user,
                password
            )
        )
    }
}
