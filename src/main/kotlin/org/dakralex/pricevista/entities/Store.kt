package org.dakralex.pricevista.entities

class Store(
    val retailer: Retailer,
    val store_id: Number? = null,
    var url_address: String? = "",
    override var country: String,
    override var adminArea: String? = "",
    override var locality: String? = "",
    override var postalCode: String? = "",
    override var streetAddress: String? = ""
) : HasExactLocation
