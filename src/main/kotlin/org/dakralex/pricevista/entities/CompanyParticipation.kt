package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias Stakeholder = Company

typealias CompanyParticipationKey = Pair<Stakeholder, Company>

/**
 * The [CompanyParticipation] relation describes the relationship between
 * stakeholders and companies they take interest in.
 */
data class CompanyParticipation(
    /** Stakeholder that takes interest in a company */
    val stakeholder: Stakeholder,

    /** The company that the stakeholder takes interest in */
    val company: Company
) : Entity