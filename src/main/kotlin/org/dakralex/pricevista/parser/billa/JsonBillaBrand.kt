package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable

@Serializable
data class JsonBillaBrand(
    val name: String,
    val slug: String
)