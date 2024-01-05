package org.dakralex.pricevista.entities

/**
 * The [Company] entity is the generalization of the different companies that
 * are somewhere in the supply chain. In this micro-world, they are retailers
 * and brands.
 */
data class Company(
    val id: Int? = null,

    /** Full name of the company **/
    var name: String,

    /** Physical location of the company's headquarters **/
    var place: Place? = null
)
