package org.dakralex.pricevista.entities

data class Language(
    val id: Int,

    /** ISO 639-1 two-letter language code **/
    val alpha2: String,

    /** ISO 639-2 three-letter language code **/
    val alpha3: String,

    /** ISO 639-1 three-digit language code **/
    val num: String,

    /** English name of the language **/
    val name: String
)
