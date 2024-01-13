package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias ArticleImageId = Int
typealias ArticleImageKey = Pair<Article, ArticleImageId>

/**
 * The [ArticleImage] relation describes which images of the articles are known.
 */
data class ArticleImage(
    /** Article the image references to **/
    val article: Article,

    var id: ArticleImageId? = null,

    /** URL address to some image of the article **/
    val imageUrl: String
) : Entity