package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.MeasurementUnit

//@formatter:off
enum class EMeasurementUnit(val unit: MeasurementUnit) {
    MILLIGRAM(MeasurementUnit(1, "mg", "milligram")),
    DECIGRAM(MeasurementUnit(2, "dg", "decigram")),
    DECAGRAM(MeasurementUnit(3, "dag", "decagram")),
    GRAM(MeasurementUnit(4, "g", "gram")),
    KILOGRAM(MeasurementUnit(5, "kg", "kilogram")),
    MILLILITRE(MeasurementUnit(6, "ml", "millilitre")),
    CENTILITRE(MeasurementUnit(7, "cl", "centilitre")),
    DECILITRE(MeasurementUnit(8, "dl", "decilitre")),
    LITRE(MeasurementUnit(9, "l", "litre")),
    PIECE(MeasurementUnit(10, "pc", "piece"))
}
//@formatter:on
