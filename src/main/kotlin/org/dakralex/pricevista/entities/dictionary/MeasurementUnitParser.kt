package org.dakralex.pricevista.entities.dictionary

import org.dakralex.pricevista.entities.MeasurementUnit
import org.dakralex.pricevista.entities.data.EMeasurementUnit

fun guessMeasurementUnit(
    longName: String? = null,
    shortName: String? = null
): MeasurementUnit? {
    val longNameLowercased = longName?.lowercase()
    val shortNameLowercased = shortName?.lowercase()

    val longNameCandidate = when (longNameLowercased) {
        "gram", "gramm" -> EMeasurementUnit.METRIC_GRAM
        "kg", "kilo", "kilogram", "kilogramm" -> EMeasurementUnit.METRIC_KILOGRAM
        "metre", "meter" -> EMeasurementUnit.METRIC_METRE
        "centimetre", "centimeter", "zentimeter" -> EMeasurementUnit.METRIC_CENTIMETRE
        "litre", "liter" -> EMeasurementUnit.METRIC_LITRE
        "millilitre", "milliliter" -> EMeasurementUnit.METRIC_MILLILITRE
        "beutel", "taschenpackung" -> EMeasurementUnit.MISC_BAG
        "riegel" -> EMeasurementUnit.MISC_BAR
        "korb" -> EMeasurementUnit.MISC_BASKET
        "blister" -> EMeasurementUnit.MISC_BLISTER
        "flasche" -> EMeasurementUnit.MISC_BOTTLE
        "box", "geschenkkarton", "schachtel" -> EMeasurementUnit.MISC_BOX
        "kübel", "eimer" -> EMeasurementUnit.MISC_BUCKET
        "bund", "bündel" -> EMeasurementUnit.MISC_BUNCH
        "dose" -> EMeasurementUnit.MISC_CAN
        "kanister" -> EMeasurementUnit.MISC_CANISTER
        "karton" -> EMeasurementUnit.MISC_CARTON
        "kiste" -> EMeasurementUnit.MISC_CRATE
        "tafel" -> EMeasurementUnit.MISC_CHOCOLATE_BAR
        "becher" -> EMeasurementUnit.MISC_CUP
        "glas", "glass" -> EMeasurementUnit.MISC_GLASS
        "tiegel", "konservenglas" -> EMeasurementUnit.MISC_JAR
        "brief" -> EMeasurementUnit.MISC_LETTER
        "netz", "net" -> EMeasurementUnit.MISC_NET
        "packung", "paket" -> EMeasurementUnit.MISC_NET
        "paar" -> EMeasurementUnit.MISC_PAIR
        "stück", "stk" -> EMeasurementUnit.MISC_PIECE
        "portion" -> EMeasurementUnit.MISC_PORTION
        "roll", "rolle", "rollen" -> EMeasurementUnit.MISC_ROLL
        "sack" -> EMeasurementUnit.MISC_SACK
        "blatt" -> EMeasurementUnit.MISC_SHEET
        "scheibe", "scheibe(slk)" -> EMeasurementUnit.MISC_SLICE
        "teebeutel" -> EMeasurementUnit.MISC_TEA_BAG
        "blechdose", "blechdose (ita)" -> EMeasurementUnit.MISC_TIN_CAN
        "spray" -> EMeasurementUnit.MISC_SPRAY
        "tasse", "schale" -> EMeasurementUnit.MISC_TRAY
        "tube" -> EMeasurementUnit.MISC_TUBE
        "waschgang" -> EMeasurementUnit.MISC_WASH_LOAD
        else -> null
    }

    if (longNameCandidate != null) {
        return longNameCandidate.unit
    }

    val shortNameCandidate = when (shortNameLowercased) {
        "g", "gr" -> EMeasurementUnit.METRIC_GRAM
        "kg" -> EMeasurementUnit.METRIC_KILOGRAM
        "m" -> EMeasurementUnit.METRIC_METRE
        "cm" -> EMeasurementUnit.METRIC_CENTIMETRE
        "l", "lt", "liter" -> EMeasurementUnit.METRIC_LITRE
        "ml" -> EMeasurementUnit.METRIC_MILLILITRE
        "st", "stk" -> EMeasurementUnit.MISC_PIECE
        else -> null
    }

    if (shortNameCandidate != null) {
        return shortNameCandidate.unit
    }

    return null
}