package org.dakralex.pricevista.contracts.parser

import java.io.File

interface JsonParser {
    fun parseEntry(file: File)
    fun parseEntries(files: List<File>)
}