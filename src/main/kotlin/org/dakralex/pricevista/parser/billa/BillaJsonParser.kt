package org.dakralex.pricevista.parser.billa

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.entities.Article
import org.dakralex.pricevista.entities.Brand
import org.dakralex.pricevista.entities.Company
import org.dakralex.pricevista.parser.JsonParser
import java.io.InputStream

private val logger = KotlinLogging.logger {}

object BillaJsonParser : JsonParser {
    @OptIn(ExperimentalSerializationApi::class)
    override fun parseEntriesFromJson(inputStream: InputStream) {
        val entries = inputStream.use {
            Json.decodeFromStream<List<BillaJsonEntry>>(it)
        }

        logger.info { "Parsed ${entries.size} entries." }

        val brandNames = entries.mapNotNull {
            it.brand?.name
        }.distinctBy { it.uppercase() }

        logger.info { "Parsed ${brandNames.size} unique brands." }
        logger.debug { "Examples: ${brandNames.slice(0..9)}" }

        // TODO Find out in which country these companies operate
        val brandCompanies = brandNames.map {
            Company(name = it, country = "AUT")
        }

        // TODO Remove product line
        val brands = brandCompanies.map {
            Brand(it, "Unknown")
        }

        val articles = entries.map { article ->
            val articleBrand = brands.find { it.name == article.brand?.name }

            Article(
                name = article.name,
                brand = articleBrand,
                description = article.descriptionLong,
                imageUrl = article.images.firstOrNull()
            )
        }
    }

}
