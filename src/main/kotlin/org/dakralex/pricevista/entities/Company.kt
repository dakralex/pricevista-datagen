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
