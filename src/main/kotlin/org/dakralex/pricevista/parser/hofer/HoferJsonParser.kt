package org.dakralex.pricevista.parser.hofer

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

// TODO Refactor so there is not so much duplicated code
object HoferJsonParser : JsonParser {
    @OptIn(ExperimentalSerializationApi::class)
    override fun parseEntries(inputStream: InputStream) {
        val entries = inputStream.use {
            Json.decodeFromStream<List<HoferJsonEntry>>(it)
        }

        logger.info { "Parsed ${entries.size} entries." }

        val brandNames = entries.mapNotNull {
            it.brand
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
            val articleBrand = brands.find { it.name == article.brand }
            val articleName =
                article.productName.replace(articleBrand?.name ?: "", "", true)

            Article(
                name = articleName,
                brand = articleBrand,
                description = article.description,
                imageUrl = article.mediaUrlLarge
            )
        }

        println(articles[0])
    }
}
