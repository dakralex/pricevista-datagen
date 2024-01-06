package org.dakralex.pricevista

import java.util.*

object Version {
    private val props = Properties()

    init {
        props.load(this::class.java.classLoader.getResourceAsStream(VERSION_FILE))
    }

    fun getVersion(): String = props.getProperty("version") ?: "unknown"
}
