package org.dakralex.pricevista.entities

class Retailer(
    private val company: Company,
    var marketShare: Double? = 0.0,
    var annualRevenue: Double? = 0.0,
    var profitMargin: Double? = 0.0,
    var workingCurrency: String = "EUR",
) {
    val company_id = company.id
}