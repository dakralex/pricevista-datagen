package org.dakralex.pricevista.entities

/**
 * The [ArticleImage] relation describes which images of the articles are known.
 */
data class ArticleImage(
    /** Article the image references to **/
    val article: Article,

    val id: Int? = null,

    /** URL address to some image of the article **/
    val imageUrl: String
)
