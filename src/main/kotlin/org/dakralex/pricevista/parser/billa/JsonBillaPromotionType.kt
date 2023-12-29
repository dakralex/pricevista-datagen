package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable

@Serializable
enum class JsonBillaPromotionType {
    FROM,
    PER_SET_OF
}