package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.ArticleDao
import org.dakralex.pricevista.contracts.dao.StoreArticleDao
import org.dakralex.pricevista.contracts.dao.StoreDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.StoreArticle
import org.dakralex.pricevista.entities.StoreArticleKey
import java.sql.ResultSet
import java.sql.Timestamp

class StoreArticleTable(
    db: Database,
    private val stores: StoreDao,
    private val articles: ArticleDao
) : StoreArticleDao,
    DatabaseTable<StoreArticle, StoreArticleKey>(
        db,
        "Store_Article",
        sequenceOf(
            "store_id",
            "store_article_id",
            "article_id",
            "since"
        )
    ) {
    override fun isUnique(entity: StoreArticle): (StoreArticle) -> Boolean {
        return { e ->
            e.store.id == entity.store.id
                    && e.storeArticleId == entity.storeArticleId
                    && e.article.id == entity.article.id
        }
    }

    override fun matchesWithId(id: StoreArticleKey): (StoreArticle) -> Boolean {
        return { e -> e.store == id.first && e.storeArticleId == id.second }
    }

    override fun mapFromResultSet(resultSet: ResultSet): StoreArticle {
        return StoreArticle(
            stores.findById(resultSet.getInt("store_id"))!!,
            resultSet.getString("store_article_id"),
            articles.findById(resultSet.getInt("article_id"))!!,
            resultSet.getTimestamp("since").toInstant()
        )
    }

    override fun mapToPropArray(entry: StoreArticle): Array<Any?> {
        return arrayOf(
            entry.store.id,
            entry.storeArticleId,
            entry.article.id,
            Timestamp.from(entry.since)
        )
    }
}