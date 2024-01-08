package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.ResolvableEntity
import org.dakralex.pricevista.database.ResolvableEntityComp

/**
 * The [ArticleImage] relation describes which images of the articles are known.
 */
data class ArticleImage(
    /** Article the image references to **/
    val article: Article,

    var id: Int? = null,

    /** URL address to some image of the article **/
    val imageUrl: String
) : ResolvableEntity {
    companion object : ResolvableEntityComp<ArticleImage> {
        override val tableName: String = "Article_Image"
        override val insertStatement: String = """
                insert into $tableName (article_id, id, image_url)
                values (:articleId, :id, :imageUrl)
            """.trimIndent()
        override val selectStatement: String = """
                select id from $tableName
                    where article_id = :articleId and
                          image_url = :imageUrl
            """.trimIndent()
    }

    override fun resolveFrom(db: Database) {
        id = db.query(selectStatement, article.id, imageUrl) {
            it.getInt("id")
        }.firstOrNull() ?: id
    }

    override fun insert(db: Database) {
        if (id == null) {
            resolveFrom(db)
        }

        db.update(insertStatement, article.id, id, imageUrl)
    }
}
