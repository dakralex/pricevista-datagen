package org.dakralex.pricevista.entities

/**
 * The [CompanyParticipation] relation describes the relationship between
 * stakeholders and companies they take interest in.
 */
data class CompanyParticipation(
    /** Stakeholder that takes interest in a company */
    val stakeholder: Company,

    /** The company that the stakeholder takes interest in */
    val company: Company
)
