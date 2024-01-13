package org.dakralex.pricevista.parser

import org.dakralex.pricevista.entities.ArticleUnit
import org.dakralex.pricevista.entities.data.EArticleUnit

fun guessArticleUnit(
    longName: String? = null,
    shortName: String? = null
): ArticleUnit? {
    val longNameLowercased = longName?.lowercase()
    val shortNameLowercased = shortName?.lowercase()

    val longNameCandidate = when (longNameLowercased) {
        "gram", "gramm" -> EArticleUnit.METRIC_GRAM
        "kg", "kilo", "kilogram", "kilogramm" -> EArticleUnit.METRIC_KILOGRAM
        "metre", "meter" -> EArticleUnit.METRIC_METRE
        "centimetre", "centimeter", "zentimeter" -> EArticleUnit.METRIC_CENTIMETRE
        "litre", "liter" -> EArticleUnit.METRIC_LITRE
        "millilitre", "milliliter" -> EArticleUnit.METRIC_MILLILITRE
        "beutel", "taschenpackung" -> EArticleUnit.MISC_BAG
        "riegel" -> EArticleUnit.MISC_BAR
        "korb" -> EArticleUnit.MISC_BASKET
        "blister" -> EArticleUnit.MISC_BLISTER
        "flasche" -> EArticleUnit.MISC_BOTTLE
        "box", "geschenkkarton", "schachtel" -> EArticleUnit.MISC_BOX
        "kübel", "eimer" -> EArticleUnit.MISC_BUCKET
        "bund", "bündel" -> EArticleUnit.MISC_BUNCH
        "dose" -> EArticleUnit.MISC_CAN
        "kanister" -> EArticleUnit.MISC_CANISTER
        "karton" -> EArticleUnit.MISC_CARTON
        "kiste" -> EArticleUnit.MISC_CRATE
        "tafel" -> EArticleUnit.MISC_CHOCOLATE_BAR
        "becher" -> EArticleUnit.MISC_CUP
        "glas", "glass" -> EArticleUnit.MISC_GLASS
        "tiegel", "konservenglas" -> EArticleUnit.MISC_JAR
        "brief" -> EArticleUnit.MISC_LETTER
        "netz", "net" -> EArticleUnit.MISC_NET
        "packung", "paket" -> EArticleUnit.MISC_NET
        "paar" -> EArticleUnit.MISC_PAIR
        "stück", "stk" -> EArticleUnit.MISC_PIECE
        "portion" -> EArticleUnit.MISC_PORTION
        "roll", "rolle", "rollen" -> EArticleUnit.MISC_ROLL
        "sack" -> EArticleUnit.MISC_SACK
        "blatt" -> EArticleUnit.MISC_SHEET
        "scheibe", "scheibe(slk)" -> EArticleUnit.MISC_SLICE
        "teebeutel" -> EArticleUnit.MISC_TEA_BAG
        "blechdose", "blechdose (ita)" -> EArticleUnit.MISC_TIN_CAN
        "spray" -> EArticleUnit.MISC_SPRAY
        "tasse", "schale" -> EArticleUnit.MISC_TRAY
        "tube" -> EArticleUnit.MISC_TUBE
        "waschgang" -> EArticleUnit.MISC_WASH_LOAD
        else -> null
    }

    if (longNameCandidate != null) {
        return longNameCandidate.unit
    }

    val shortNameCandidate = when (shortNameLowercased) {
        "g", "gr" -> EArticleUnit.METRIC_GRAM
        "kg" -> EArticleUnit.METRIC_KILOGRAM
        "m" -> EArticleUnit.METRIC_METRE
        "cm" -> EArticleUnit.METRIC_CENTIMETRE
        "l", "lt", "liter" -> EArticleUnit.METRIC_LITRE
        "ml" -> EArticleUnit.METRIC_MILLILITRE
        "st", "stk" -> EArticleUnit.MISC_PIECE
        else -> null
    }

    if (shortNameCandidate != null) {
        return shortNameCandidate.unit
    }

    return null
}