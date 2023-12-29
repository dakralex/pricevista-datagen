package org.dakralex.pricevista.entities

class Article(
    val id: Number? = null,
    var name: String,
    var description: String? = null,
    var originCountry: String? = null,
    var imageUrl: String? = null,
    var brand: Brand? = null,
    var category: Category? = null
)

