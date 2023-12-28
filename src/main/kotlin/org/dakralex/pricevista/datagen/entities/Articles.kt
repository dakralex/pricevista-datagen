package org.dakralex.pricevista.datagen.entities

class Article(
    val id: Number? = null,
    var name: String,
    var description: String? = null,
    var originCountry: String? = null,
    var imageUrl: String? = null,
    var brand: Brand? = null,
    var category: Category? = null
)

class ArticleVariant(
    val id: Number? = null,
    val article: Article,
    var unit: String,
    var quantity: Double,
    var weightable: Boolean
)

class Category(
    val id: Number? = null,
    var name: String,
    var description: String? = null
)
