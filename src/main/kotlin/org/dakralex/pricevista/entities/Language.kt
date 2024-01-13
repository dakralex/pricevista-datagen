package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias LanguageId = Int

/**
 * The [Language] entity describes a language that is represented in the
 * ISO 639-1 standard definition.
 */
data class Language(
    /** ISO 639-1 three-digit language code **/
    val id: LanguageId,

    /** ISO 639-1 two-letter language code **/
    val alpha2: String,

    /** ISO 639-2 three-letter language code **/
    val alpha3: String,

    /** English name of the language **/
    val name: String
) : Entity