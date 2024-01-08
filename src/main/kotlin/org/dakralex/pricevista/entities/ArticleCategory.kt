package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

/**
 * The [ArticleCategory] relation describes the categories an article
 * belongs to.
 */
data class ArticleCategory(
    val article: Article,

    /** Category the article belongs to **/
    val category: Category
) : Entity {
    companion object : EntityComp<ArticleCategory> {
        override val tableName: String = "Article_Category"
        override val insertStatement: String = """
                insert into $tableName (article_id, category_id)
                values (:articleId, :categoryId)
            """.trimIndent()
    }

    override fun insert(db: Database) {
        db.update(insertStatement, article.id, category.id)
    }
}
