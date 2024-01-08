package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [ArticleImage] relation describes which images of the articles are known.
 */
data class ArticleImage(
    /** Article the image references to **/
    val article: Article,

    val id: Int? = null,

    /** URL address to some image of the article **/
    val imageUrl: String
) : Entity {
    companion object {
        const val tableName: String = "Article_Image"
        const val insertStatement: String =
            """insert into $tableName (article_id, id, image_url) values (:articleId, :id, :imageUrl)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, article.id, id, imageUrl)
    }
}
