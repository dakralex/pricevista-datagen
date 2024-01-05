package org.dakralex.pricevista.entities

/**
 * The [ArticleCategory] relation describes the categories an article
 * belongs to.
 */
data class ArticleCategory(
    val article: Article,

    /** Category the article belongs to **/
    val category: Category
)
