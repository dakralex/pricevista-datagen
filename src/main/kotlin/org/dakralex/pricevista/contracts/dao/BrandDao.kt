package org.dakralex.pricevista.contracts.dao

import org.dakralex.pricevista.entities.Brand
import org.dakralex.pricevista.entities.BrandId

interface BrandDao : EntityDao<Brand, BrandId> {
    fun findByShortName(shortName: String): Brand?
}