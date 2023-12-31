package org.dakralex.pricevista.parser

import com.aayushatharva.brotli4j.Brotli4jLoader
import com.aayushatharva.brotli4j.decoder.Decoder
import com.aayushatharva.brotli4j.decoder.DecoderJNI
import com.aayushatharva.brotli4j.decoder.DirectDecompress
import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream


private val logger = KotlinLogging.logger {}

interface JsonParser {
    fun parseEntries(inputStream: InputStream)

    fun parseEntries(files: List<File>) {
        logger.info { "Parsing ${files.size} JSON files..." }

        val inputStreams = files.map {
            when (it.extension) {
                "json" -> it.inputStream()
                "br" -> decodeBrotliFile(it)
                else -> TODO("Not implemented")
            }
        }

        inputStreams.map { parseEntries(it) }
    }
}

fun decodeBrotliFile(it: File): ByteArrayInputStream {
    Brotli4jLoader.ensureAvailability()

    logger.info { "Decompressing ${it.name}..." }

    val directDecompress: DirectDecompress =
        Decoder.decompress(it.inputStream().readAllBytes())

    when (directDecompress.resultStatus) {
        DecoderJNI.Status.DONE -> logger.info { "Successfully decompressed ${it.name}." }
        else -> logger.error { "Some error occurred while decompressing ${it.name}." }
    }

    return directDecompress.getDecompressedData().inputStream()
}
