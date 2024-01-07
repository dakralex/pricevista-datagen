package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.MeasurementUnit

//@formatter:off
enum class EMeasurementUnit(val unit: MeasurementUnit) {
    // MASS UNITS
    METRIC_KILOGRAM(MeasurementUnit(1, "kg", "kilogram")),
    METRIC_GRAM(MeasurementUnit(2, "g", "gram")),
    METRIC_DECIGRAM(MeasurementUnit(3, "dg", "decigram")),
    METRIC_MILLIGRAM(MeasurementUnit(4, "mg", "milligram")),
    METRIC_MICROGRAM(MeasurementUnit(5, "μg", "microgram")),
    METRIC_NANOGRAM(MeasurementUnit(6, "ng", "nanogram")),
    METRIC_DECAGRAM(MeasurementUnit(7, "dag", "decagram")),
    METRIC_TONNE(MeasurementUnit(8, "t", "tonne", "tonne")),
    IMPERIAL_POUND(MeasurementUnit(9, "lb", "pound")),
    IMPERIAL_OUNCE(MeasurementUnit(10, "oz", "ounce")),

    // LENGTH UNITS
    METRIC_METRE(MeasurementUnit(20, "m", "metre")),
    METRIC_DECIMETRE(MeasurementUnit(21, "dm", "decimetre")),
    METRIC_CENTIMETRE(MeasurementUnit(22, "cm", "centimetre")),
    METRIC_MILLIMETRE(MeasurementUnit(23, "mm", "millimetre")),
    METRIC_MICROMETRE(MeasurementUnit(24, "µm", "micrometre")),
    METRIC_NANOMETRE(MeasurementUnit(25, "nm", "nanometre")),
    METRIC_KILOMETRE(MeasurementUnit(26, "km", "kilometre")),
    IMPERIAL_INCH(MeasurementUnit(27, "in", "inch", "inches")),
    IMPERIAL_FOOT(MeasurementUnit(28, "ft", "foot", "feet")),
    IMPERIAL_YARD(MeasurementUnit(29, "yd", "yard")),
    IMPERIAL_MILE(MeasurementUnit(30, "mi", "mile")),
    IMPERIAL_THOU(MeasurementUnit(31, "mil", "thou")),

    // AREA UNITS
    // RESERVED

    // VOLUME UNITS
    METRIC_CUBIC_METRE(MeasurementUnit(60, "m³", "cubic metre")),
    METRIC_CUBIC_DECIMETRE(MeasurementUnit(61, "dm³", "cubic decimetre")),
    METRIC_CUBIC_CENTIMETRE(MeasurementUnit(62, "cm³", "cubic centimetre")),
    METRIC_CUBIC_MILLIMETRE(MeasurementUnit(63, "mm³", "cubic millimetre")),
    METRIC_CUBIC_KILOMETRE(MeasurementUnit(64, "km³", "cubic kilometre")),
    METRIC_LITRE(MeasurementUnit(65, "l", "litre")),
    METRIC_DECILITRE(MeasurementUnit(66, "dl", "decilitre")),
    METRIC_CENTILITRE(MeasurementUnit(67, "cl", "centilitre")),
    METRIC_MILLILITRE(MeasurementUnit(68, "ml", "millilitre")),
    METRIC_MICROLITRE(MeasurementUnit(69, "µl", "microlitre")),
    METRIC_HECTOLITRE(MeasurementUnit(70, "hl", "hectolitre")),
    IMPERIAL_GALLON(MeasurementUnit(71, "imp gal", "imperial gallon")),
    IMPERIAL_QUART(MeasurementUnit(72, "imp qt", "imperial quart", "imperial quart")),
    IMPERIAL_CUBIC_INCH(MeasurementUnit(73, "cu in", "cubic inch", "cubic inches")),
    US_LIQUID_GALLON(MeasurementUnit(74, "US gal", "US gallon")),
    US_DRY_GALLON(MeasurementUnit(75, "US dry gal", "US dry gallon")),
    US_LIQUID_QUART(MeasurementUnit(76, "US qt", "liquid quart", "liquid quart")),
    US_DRY_QUART(MeasurementUnit(77, "US dry qt", "US dry quart", "US dry quart")),

    // MISCELLANEOUS UNITS
    MISC_BAG(MeasurementUnit(100, "bag")),
    MISC_BAR(MeasurementUnit(101, "bar")),
    MISC_BASKET(MeasurementUnit(102, "basket")),
    MISC_BLISTER(MeasurementUnit(103, "blister pack")),
    MISC_BOTTLE(MeasurementUnit(104, "bottle")),
    MISC_BOX(MeasurementUnit(105, "box", pluralName = "boxes")),
    MISC_BUCKET(MeasurementUnit(106, "bucket")),
    MISC_BUNCH(MeasurementUnit(107, "bunch", pluralName = "bunches")),
    MISC_CAN(MeasurementUnit(108, "can", "can")),
    MISC_CANISTER(MeasurementUnit(109, "canister")),
    MISC_CARTON(MeasurementUnit(110, "carton", "cardboard box", "cardboard boxes")),
    MISC_CRATE(MeasurementUnit(111, "crate")),
    MISC_CHOCOLATE_BAR(MeasurementUnit(112, "chocolate bar")),
    MISC_CUP(MeasurementUnit(113, "cup")),
    MISC_GLASS(MeasurementUnit(114, "glass", pluralName = "glasses")),
    MISC_JAR(MeasurementUnit(115, "jar")),
    MISC_LETTER(MeasurementUnit(116, "letter")),
    MISC_NET(MeasurementUnit(117, "net")),
    MISC_PACKAGE(MeasurementUnit(118, "package")),
    MISC_PAIR(MeasurementUnit(119, "pair")),
    MISC_PIECE(MeasurementUnit(120, "piece")),
    MISC_PORTION(MeasurementUnit(121, "portion")),
    MISC_ROLL(MeasurementUnit(122, "roll")),
    MISC_SACK(MeasurementUnit(123, "sack")),
    MISC_SHEET(MeasurementUnit(124, "sheet")),
    MISC_SLICE(MeasurementUnit(125, "slice")),
    MISC_SPRAY(MeasurementUnit(126, "spray")),
    MISC_TEA_BAG(MeasurementUnit(127, "bag", "tea bag")),
    MISC_TIN_CAN(MeasurementUnit(128, "can", "tin can")),
    MISC_TRAY(MeasurementUnit(129, "tray")),
    MISC_TUBE(MeasurementUnit(130, "tube")),
    MISC_WASH_LOAD(MeasurementUnit(131, "load", "wash load")),
}
//@formatter:on
