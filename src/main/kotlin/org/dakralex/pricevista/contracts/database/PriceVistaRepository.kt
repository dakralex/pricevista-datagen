package org.dakralex.pricevista.contracts.database

import org.dakralex.pricevista.contracts.dao.*
import org.dakralex.pricevista.entities.*

interface PriceVistaRepository {
    fun init(): PriceVistaRepository
    fun commit(): PriceVistaRepository
    fun drop(): PriceVistaRepository

    fun addCompanies(entries: Sequence<Company>): CompanyDao
    fun addBrands(entries: Sequence<Brand>): BrandDao
    fun addArticles(entries: Sequence<Article>): ArticleDao
    fun addArticleImages(entries: Sequence<ArticleImage>): ArticleImageDao
    fun addStoreArticles(entries: Sequence<StoreArticle>): StoreArticleDao
    fun addRecordedPrices(entries: Sequence<RecordedPrice>): RecordedPriceDao
}