package org.dakralex.pricevista.database

import org.dakralex.pricevista.entities.Brand
import org.dakralex.pricevista.entities.Company
import org.dakralex.pricevista.entities.Retailer

class PartialUpdate(
    val companies: List<Company> = emp(),
    val retailers: List<Retailer> = emptyList(),
    val brands: List<Brand> = emptyList(),
) {

}