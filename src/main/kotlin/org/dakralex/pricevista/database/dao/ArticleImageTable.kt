package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.ArticleDao
import org.dakralex.pricevista.contracts.dao.ArticleImageDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.ArticleImage
import org.dakralex.pricevista.entities.ArticleImageKey
import java.sql.ResultSet

class ArticleImageTable(
    db: Database,
    private val articles: ArticleDao
) : ArticleImageDao,
    DatabaseTable<ArticleImage, ArticleImageKey>(
        db,
        "Article_Image",
        sequenceOf(
            "article_id",
            "id",
            "image_url"
        )
    ) {

    override fun matchesWithId(id: ArticleImageKey): (ArticleImage) -> Boolean {
        return { e -> e.article.id == id.first.id && e.id == id.second }
    }

    override fun mapFromResultSet(resultSet: ResultSet): ArticleImage {
        return ArticleImage(
            articles.findById(resultSet.getInt("article_id"))!!,
            resultSet.getInt("id"),
            resultSet.getString("image_url")
        )
    }

    override fun mapToPropArray(entry: ArticleImage): Array<Any?> {
        return arrayOf(
            entry.article,
            entry.id,
            entry.imageUrl
        )
    }
}