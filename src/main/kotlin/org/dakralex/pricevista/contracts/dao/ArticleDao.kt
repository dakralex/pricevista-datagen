package org.dakralex.pricevista.contracts.dao

import org.dakralex.pricevista.entities.Article
import org.dakralex.pricevista.entities.ArticleId
import org.dakralex.pricevista.entities.ArticleUnit
import org.dakralex.pricevista.entities.Brand

interface ArticleDao : EntityDao<Article, ArticleId> {
    fun findByProps(
        brand: Brand?,
        name: String,
        articleUnit: ArticleUnit,
        quantity: Double
    ): Article?
}