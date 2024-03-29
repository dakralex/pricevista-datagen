package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable

@Serializable
data class BillaJsonParentCategory(
    val key: String,
    val name: String,
    val slug: String,
    val orderHint: String
)