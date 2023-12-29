package org.dakralex.pricevista.parser.hofer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoferJsonProductMediaListItem(
    @SerialName("MediaUrlL")
    val mediaUrlLarge: String? = null
)