package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias CurrencyId = Int

/**
 * The [Currency] entity describes a currency that is represented in the
 * ISO 4217 standard definition.
 */
data class Currency(
    /** ISO 4217 three-digit currency code **/
    val id: CurrencyId,

    /** ISO 4217 three-letter currency code **/
    val alpha3: String,

    /** Number of digits for the subunit */
    val scale: Int,

    /** Symbol of the currency **/
    val symbol: String,

    /** Symbol of the currency's subunit */
    val minor: String? = null,

    /** English name of the currency **/
    val name: String
) : Entity