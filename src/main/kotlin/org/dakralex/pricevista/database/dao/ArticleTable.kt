package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.ArticleDao
import org.dakralex.pricevista.contracts.dao.ArticleUnitDao
import org.dakralex.pricevista.contracts.dao.BrandDao
import org.dakralex.pricevista.contracts.dao.CountryDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Article
import org.dakralex.pricevista.entities.ArticleId
import java.sql.ResultSet

class ArticleTable(
    db: Database,
    private val brands: BrandDao,
    private val countries: CountryDao,
    private val articleUnits: ArticleUnitDao
) : ArticleDao,
    DatabaseTable<Article, ArticleId>(
        db,
        "Article",
        sequenceOf(
            "id",
            "brand_id",
            "name",
            "description",
            "origin_country_id",
            "article_unit_id",
            "quantity",
            "weightable"
        )
    ) {
    override fun isUnique(entity: Article): (Article) -> Boolean {
        return if (entity.id == null) { e ->
            e.brand == entity.brand
                    && e.name == entity.name
                    && e.articleUnit == entity.articleUnit
                    && e.quantity == entity.quantity
        } else { e ->
            e.id == entity.id
        }
    }

    override fun matchesWithId(id: ArticleId): (Article) -> Boolean {
        return { e -> e.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Article {
        return Article(
            resultSet.getInt("id"),
            brands.findById(resultSet.getInt("brand_id")),
            resultSet.getString("name"),
            resultSet.getString("description"),
            countries.findById(resultSet.getInt("origin_country_id")),
            articleUnits.findById(resultSet.getInt("article_unit_id"))!!,
            resultSet.getBigDecimal("quantity"),
            resultSet.getInt("weightable") == 1
        )
    }

    override fun mapToPropArray(entry: Article): Array<Any?> {
        return arrayOf(
            entry.id,
            entry.brand?.company?.id,
            entry.name,
            entry.description,
            entry.originCountry?.id,
            entry.articleUnit.id,
            entry.quantity,
            entry.weightable
        )
    }
}