package org.dakralex.pricevista.datagen.entities

import java.util.*

class Store(
    val retailer: Retailer,
    val store_id: Number? = null,
    var url_address: String? = "",
    override var country: String,
    override var adminArea: String? = "",
    override var locality: String? = "",
    override var postalCode: String? = "",
    override var streetAddress: String? = ""
) : HasExactLocation

class StoreArticleMap(
    val store: Store,
    val storeArticleId: String,
    var articleVariant: ArticleVariant,
    val since: Date
)

class StoreCategoryMap(
    val store: Store,
    val storeCategoryId: String,
    var category: Category
)
