package org.dakralex.pricevista.parser.spar

import kotlinx.serialization.Serializable
import org.dakralex.pricevista.parser.JsonEntry

@Serializable
data class SparJsonEntry(
    val id: String,
    val position: Int,
    val score: Double,
    val foundWords: List<String>? = null,
    val variantValues: List<String>? = null,
    val masterValues: SparJsonProduct
) : JsonEntry