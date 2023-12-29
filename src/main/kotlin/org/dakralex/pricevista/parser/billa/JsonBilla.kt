package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.dakralex.pricevista.entities.Article
import org.dakralex.pricevista.entities.Brand
import org.dakralex.pricevista.entities.Company
import java.io.InputStream
import kotlin.math.min


@OptIn(ExperimentalSerializationApi::class)
fun parseJsonBillaEntries(inputStream: InputStream) {
    val entries = inputStream.use { Json.decodeFromStream<List<JsonBillaEntry>>(it) }

    println("Parsed ${entries.size} entries from Billa JSON export.")

    val brandNames = entries.mapNotNull { it.brand?.name }.distinctBy { it.uppercase() }

    println("Parsed ${brandNames.size} unique brands, here are ${min(brandNames.size, 10)} examples:")
    println("${brandNames.slice(0..9)}")

    // TODO Find out in which country these companies operate
    val brandCompanies = brandNames.map { Company(name = it, country = "AUT") }

    // TODO Remove product line
    val brands = brandCompanies.map { Brand(it, "Unknown") }

    val articles = entries.map { article ->
        val articleBrand = brands.find { it.name == article.brand?.name }

        Article(name = article.name, brand = articleBrand, description = article.descriptionLong, imageUrl = article.images.firstOrNull())
    }

    val json = Json { prettyPrint = true }

    println(json.encodeToString(articles.slice(1..10)))
}