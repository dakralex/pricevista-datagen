package org.dakralex.pricevista.entities

import java.util.*

class StoreArticleMap(
    val store: Store,
    val storeArticleId: String,
    var articleVariant: ArticleVariant,
    val since: Date
)