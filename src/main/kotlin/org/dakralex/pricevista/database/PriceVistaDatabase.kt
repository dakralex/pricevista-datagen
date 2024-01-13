package org.dakralex.pricevista.database

import org.dakralex.pricevista.contracts.dao.*
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.contracts.database.PriceVistaRepository
import org.dakralex.pricevista.database.dao.*
import org.dakralex.pricevista.entities.*

class PriceVistaDatabase(val db: Database) : PriceVistaRepository {
    private val countries = CountryTable(db)
    private val currencies = CurrencyTable(db)
    private val languages = LanguageTable(db)
    private val articleUnits = ArticleUnitTable(db)
    private val places = PlaceTable(db, countries)

    private val companies = CompanyTable(db, places)
    private val retailers = RetailerTable(db, companies)
    private val brands = BrandTable(db, companies)
    private val companyParticipations = CompanyParticipationTable(db, companies)

    private val articles = ArticleTable(db, brands, countries, articleUnits)
    private val articleImages = ArticleImageTable(db, articles)

    private val stores =
        StoreTable(db, retailers, places, currencies, languages)
    private val storeArticles = StoreArticleTable(db, stores, articles)
    private val currentPrices = CurrentPriceTable(db, stores, articles)
    private val recordedPrices = RecordedPriceTable(db, stores, articles)

    override fun init(): PriceVistaDatabase {
        countries.initialize()
        currencies.initialize()
        languages.initialize()
        articleUnits.initialize()
        places.initialize()

        companies.initialize()
        retailers.initialize()
        brands.initialize()
        companyParticipations.initialize()

        articles.initialize()
        articleImages.initialize()

        stores.initialize()
        storeArticles.initialize()
        currentPrices.initialize()
        recordedPrices.initialize()

        return this
    }

    override fun commit(): PriceVistaDatabase {
        countries.commit()
        currencies.commit()
        languages.commit()
        articleUnits.commit()
        places.commit()

        companies.commit()
        retailers.commit()
        brands.commit()
        companyParticipations.commit()

        articles.commit()
        articleImages.commit()

        stores.commit()
        storeArticles.commit()
        currentPrices.commit()
        recordedPrices.commit()

        return this
    }

    override fun drop(): PriceVistaDatabase {
        recordedPrices.dropTable()
        currentPrices.dropTable()
        storeArticles.dropTable()
        stores.dropTable()

        articleImages.dropTable()
        articles.dropTable()

        companyParticipations.dropTable()
        brands.dropTable()
        retailers.dropTable()
        companies.dropTable()

        places.dropTable()
        articleUnits.dropTable()
        languages.dropTable()
        currencies.dropTable()
        countries.dropTable()

        return this
    }

    override fun addCompanies(entries: Sequence<Company>): CompanyDao {
        companies.addBatch(entries)

        return companies
    }

    override fun addBrands(entries: Sequence<Brand>): BrandDao {
        brands.addBatch(entries)

        return brands
    }

    override fun addArticles(entries: Sequence<Article>): ArticleDao {
        articles.addBatch(entries)

        return articles
    }

    override fun addArticleImages(entries: Sequence<ArticleImage>): ArticleImageDao {
        articleImages.addBatch(entries)

        return articleImages
    }

    override fun addStoreArticles(entries: Sequence<StoreArticle>): StoreArticleDao {
        storeArticles.addBatch(entries)

        return storeArticles
    }

    override fun addRecordedPrices(entries: Sequence<RecordedPrice>): RecordedPriceDao {
        recordedPrices.addBatch(entries)

        return recordedPrices
    }
}