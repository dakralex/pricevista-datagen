package org.dakralex.pricevista.entities

/**
 * The [MeasurementUnit] entity describes units something can be measured in.
 */
data class MeasurementUnit(
    val id: Int? = null,

    /** Symbol of the measurement unit **/
    val symbol: String,

    /** English name of the measurement unit **/
    val name: String,
)
