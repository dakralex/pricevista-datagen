package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.ArticleUnit

//@formatter:off
enum class EArticleUnit(val unit: ArticleUnit) {
    // MASS UNITS
    METRIC_KILOGRAM(ArticleUnit(1, "kg", "kilogram")),
    METRIC_GRAM(ArticleUnit(2, "g", "gram")),
    METRIC_DECIGRAM(ArticleUnit(3, "dg", "decigram")),
    METRIC_MILLIGRAM(ArticleUnit(4, "mg", "milligram")),
    METRIC_MICROGRAM(ArticleUnit(5, "μg", "microgram")),
    METRIC_NANOGRAM(ArticleUnit(6, "ng", "nanogram")),
    METRIC_DECAGRAM(ArticleUnit(7, "dag", "decagram")),
    METRIC_TONNE(ArticleUnit(8, "t", "tonne", "tonne")),
    IMPERIAL_POUND(ArticleUnit(9, "lb", "pound")),
    IMPERIAL_OUNCE(ArticleUnit(10, "oz", "ounce")),

    // LENGTH UNITS
    METRIC_METRE(ArticleUnit(20, "m", "metre")),
    METRIC_DECIMETRE(ArticleUnit(21, "dm", "decimetre")),
    METRIC_CENTIMETRE(ArticleUnit(22, "cm", "centimetre")),
    METRIC_MILLIMETRE(ArticleUnit(23, "mm", "millimetre")),
    METRIC_MICROMETRE(ArticleUnit(24, "µm", "micrometre")),
    METRIC_NANOMETRE(ArticleUnit(25, "nm", "nanometre")),
    METRIC_KILOMETRE(ArticleUnit(26, "km", "kilometre")),
    IMPERIAL_INCH(ArticleUnit(27, "in", "inch", "inches")),
    IMPERIAL_FOOT(ArticleUnit(28, "ft", "foot", "feet")),
    IMPERIAL_YARD(ArticleUnit(29, "yd", "yard")),
    IMPERIAL_MILE(ArticleUnit(30, "mi", "mile")),
    IMPERIAL_THOU(ArticleUnit(31, "mil", "thou")),

    // AREA UNITS
    // RESERVED

    // VOLUME UNITS
    METRIC_CUBIC_METRE(ArticleUnit(60, "m³", "cubic metre")),
    METRIC_CUBIC_DECIMETRE(ArticleUnit(61, "dm³", "cubic decimetre")),
    METRIC_CUBIC_CENTIMETRE(ArticleUnit(62, "cm³", "cubic centimetre")),
    METRIC_CUBIC_MILLIMETRE(ArticleUnit(63, "mm³", "cubic millimetre")),
    METRIC_CUBIC_KILOMETRE(ArticleUnit(64, "km³", "cubic kilometre")),
    METRIC_LITRE(ArticleUnit(65, "l", "litre")),
    METRIC_DECILITRE(ArticleUnit(66, "dl", "decilitre")),
    METRIC_CENTILITRE(ArticleUnit(67, "cl", "centilitre")),
    METRIC_MILLILITRE(ArticleUnit(68, "ml", "millilitre")),
    METRIC_MICROLITRE(ArticleUnit(69, "µl", "microlitre")),
    METRIC_HECTOLITRE(ArticleUnit(70, "hl", "hectolitre")),
    IMPERIAL_GALLON(ArticleUnit(71, "imp gal", "imperial gallon")),
    IMPERIAL_QUART(ArticleUnit(72, "imp qt", "imperial quart", "imperial quart")),
    IMPERIAL_CUBIC_INCH(ArticleUnit(73, "cu in", "cubic inch", "cubic inches")),
    US_LIQUID_GALLON(ArticleUnit(74, "US gal", "US gallon")),
    US_DRY_GALLON(ArticleUnit(75, "US dry gal", "US dry gallon")),
    US_LIQUID_QUART(ArticleUnit(76, "US qt", "liquid quart", "liquid quart")),
    US_DRY_QUART(ArticleUnit(77, "US dry qt", "US dry quart", "US dry quart")),

    // MISCELLANEOUS UNITS
    MISC_BAG(ArticleUnit(100, "bag")),
    MISC_BAR(ArticleUnit(101, "bar")),
    MISC_BASKET(ArticleUnit(102, "basket")),
    MISC_BLISTER(ArticleUnit(103, "blister pack")),
    MISC_BOTTLE(ArticleUnit(104, "bottle")),
    MISC_BOX(ArticleUnit(105, "box", pluralName = "boxes")),
    MISC_BUCKET(ArticleUnit(106, "bucket")),
    MISC_BUNCH(ArticleUnit(107, "bunch", pluralName = "bunches")),
    MISC_CAN(ArticleUnit(108, "can", "can")),
    MISC_CANISTER(ArticleUnit(109, "canister")),
    MISC_CARTON(ArticleUnit(110, "carton", "cardboard box", "cardboard boxes")),
    MISC_CRATE(ArticleUnit(111, "crate")),
    MISC_CHOCOLATE_BAR(ArticleUnit(112, "chocolate bar")),
    MISC_CUP(ArticleUnit(113, "cup")),
    MISC_GLASS(ArticleUnit(114, "glass", pluralName = "glasses")),
    MISC_JAR(ArticleUnit(115, "jar")),
    MISC_LETTER(ArticleUnit(116, "letter")),
    MISC_NET(ArticleUnit(117, "net")),
    MISC_PACKAGE(ArticleUnit(118, "package")),
    MISC_PAIR(ArticleUnit(119, "pair")),
    MISC_PIECE(ArticleUnit(120, "piece")),
    MISC_PORTION(ArticleUnit(121, "portion")),
    MISC_ROLL(ArticleUnit(122, "roll")),
    MISC_SACK(ArticleUnit(123, "sack")),
    MISC_SHEET(ArticleUnit(124, "sheet")),
    MISC_SLICE(ArticleUnit(125, "slice")),
    MISC_SPRAY(ArticleUnit(126, "spray")),
    MISC_TEA_BAG(ArticleUnit(127, "bag", "tea bag")),
    MISC_TIN_CAN(ArticleUnit(128, "can", "tin can")),
    MISC_TRAY(ArticleUnit(129, "tray")),
    MISC_TUBE(ArticleUnit(130, "tube")),
    MISC_WASH_LOAD(ArticleUnit(131, "load", "wash load")),
}
//@formatter:on
