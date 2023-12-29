package org.dakralex.pricevista.parser

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import java.io.InputStream

private val logger = KotlinLogging.logger {}

interface JsonParser {
    fun parseEntriesFromJson(inputStream: InputStream)

    fun parseEntriesFromJson(files: List<File>) {
        logger.info { "Parsing ${files.size} JSON files..." }

        files.map { parseEntriesFromJson(it.inputStream()) }
    }
}
