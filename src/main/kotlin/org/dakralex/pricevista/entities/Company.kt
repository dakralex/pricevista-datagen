package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias CompanyId = Int

/**
 * The [Company] entity is the generalization of the different companies that
 * are somewhere in the supply chain. In this micro-world, they are retailers
 * and brands.
 */
data class Company(
    var id: CompanyId? = null,

    /** Full name of the company **/
    var longName: String? = null,

    /** Short name of the company **/
    var shortName: String,

    /** Physical location of the company's headquarters **/
    var place: Place? = null
) : Entity {

    companion object {
        fun fromShortName(name: String): Company {
            return Company(shortName = name)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Company

        return shortName.equals(other.shortName, true)
    }

    override fun hashCode(): Int {
        return shortName.lowercase().hashCode()
    }
}