package org.dakralex.pricevista.parser.hofer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoferJsonIcon(
    @SerialName("blobURL")
    val blobUrl: String? = null,
    val iconTooltip: String? = null
)