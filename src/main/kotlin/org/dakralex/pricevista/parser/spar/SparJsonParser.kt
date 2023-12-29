package org.dakralex.pricevista.parser.spar

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
object SparJsonParser : JsonParser {
    @OptIn(ExperimentalSerializationApi::class)
    override fun parseEntries(inputStream: InputStream) {
        val products = inputStream.use {
            Json.decodeFromStream<List<SparJsonEntry>>(it)
        }.map { it.masterValues }

        logger.info { "Parsed ${products.size} entries." }

        val brandNames = products.mapNotNull {
            it.brand?.first()
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

        val articles = products.map { article ->
            val articleBrand = brands.find { it.name == article.brand?.first() }
            val articleName =
                article.name

            Article(
                name = articleName,
                brand = articleBrand,
                description = article.description,
                imageUrl = article.imageUrl
            )
        }

        println(articles[0])
    }
}
