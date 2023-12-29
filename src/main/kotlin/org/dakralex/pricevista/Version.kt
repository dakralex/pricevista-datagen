package org.dakralex.pricevista

import java.util.*

object Version {
    private val versionProps = Properties()

    init {
        versionProps.load(this::class.java.classLoader.getResourceAsStream("version.properties"))
    }

    fun getVersion(): String = versionProps.getProperty("version") ?: "unknown"
}
