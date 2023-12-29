package org.dakralex.pricevista.entities

class ArticleVariant(
    val id: Number? = null,
    val article: Article,
    var unit: String,
    var quantity: Double,
    var weightable: Boolean
)