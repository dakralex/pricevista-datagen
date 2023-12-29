package org.dakralex.pricevista.entities

class Brand(
    private val company: Company,
    var productLine: String? = null
) {
    val name: String = company.name
}