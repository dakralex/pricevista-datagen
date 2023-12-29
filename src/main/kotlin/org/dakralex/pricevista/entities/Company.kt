package org.dakralex.pricevista.entities

class Company(
    val id: Number? = null,
    var name: String,
    override var country: String,
    override var adminArea: String? = "",
    override var locality: String? = "",
    override var postalCode: String? = "",
    override var streetAddress: String? = ""
) : HasExactLocation

class Retailer(
    private val company: Company,
    var marketShare: Double? = 0.0,
    var annualRevenue: Double? = 0.0,
    var profitMargin: Double? = 0.0,
    var workingCurrency: String = "EUR",
) {
    val company_id = company.id
}

class Brand(
    private val company: Company,
    var productLine: String? = null
)

class CompanyOwnership(
    val ownerId: Number,
    val owneeId: Number
)
