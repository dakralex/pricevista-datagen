package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [ArticleCategory] relation describes the categories an article
 * belongs to.
 */
data class ArticleCategory(
    val article: Article,

    /** Category the article belongs to **/
    val category: Category
) : Entity {
    companion object {
        const val tableName: String = "Article_Category"
        const val insertStatement: String =
            """insert into $tableName (article_id, category_id) values (:articleId, :categoryId)"""
    }

    override fun insert(db: Database) {
        db.update(insertStatement, article.id, category.id)
    }
}
