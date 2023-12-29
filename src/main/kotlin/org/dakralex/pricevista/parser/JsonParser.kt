package org.dakralex.pricevista.parser

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import java.io.InputStream

private val logger = KotlinLogging.logger {}

interface JsonParser {
    fun parseEntries(inputStream: InputStream)

    fun parseEntries(files: List<File>) {
        logger.info { "Parsing ${files.size} JSON files..." }

        files.map { parseEntries(it.inputStream()) }
    }
}
