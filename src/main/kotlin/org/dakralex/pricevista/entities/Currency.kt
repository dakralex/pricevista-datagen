package org.dakralex.pricevista.entities

data class Currency(
    /** ISO 4217 three-digit currency code **/
    val id: Int,

    /** ISO 4217 three-letter currency code **/
    val alpha3: String,

    /** Number of digits for the subunit */
    val subExponent: Int,

    /** Symbol of the currency **/
    val symbol: String,

    /** Symbol of the currency's subunit */
    val subSymbol: String? = null,

    /** English name of the currency **/
    val name: String
)
