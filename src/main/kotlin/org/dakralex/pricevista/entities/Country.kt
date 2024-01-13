package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias CountryId = Int

/**
 * The [Country] entity describes a country that is represented in the
 * ISO 3166-1 standard definition.
 */
data class Country(
    /** ISO 3166-1 numeric three-digit country code **/
    val id: CountryId,

    /** ISO 3166-1 alpha-2 two-letter country code **/
    val alpha2: String,

    /** ISO 3166-1 alpha-3 three-letter country code **/
    val alpha3: String,

    /** English name of the country **/
    val name: String,
) : Entity