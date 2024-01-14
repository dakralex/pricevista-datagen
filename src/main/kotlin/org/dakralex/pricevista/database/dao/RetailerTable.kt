package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.CompanyDao
import org.dakralex.pricevista.contracts.dao.RetailerDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Retailer
import org.dakralex.pricevista.entities.RetailerId
import org.dakralex.pricevista.entities.data.ERetailer
import java.sql.ResultSet

class RetailerTable(
    db: Database,
    private val companies: CompanyDao
) : RetailerDao,
    DatabaseTable<Retailer, RetailerId>(
        db,
        "Retailer",
        sequenceOf(
            "company_id",
            "website_url"
        )
    ) {
    override fun initialize(): Boolean {
        if (super.initialize()) {
            addBatch(ERetailer.entries.asSequence().map(ERetailer::retailer))
            return true
        }

        return false
    }

    override fun isUnique(entity: Retailer): (Retailer) -> Boolean {
        return { e -> e.company.id == entity.company.id }
    }

    override fun matchesWithId(id: RetailerId): (Retailer) -> Boolean {
        return { e -> e.company.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Retailer {
        return Retailer(
            companies.findById(resultSet.getInt("company_id"))!!,
            resultSet.getString("website_url")
        )
    }

    override fun mapToPropArray(entry: Retailer): Array<Any?> {
        return arrayOf(
            entry.company.id,
            entry.websiteUrl
        )
    }

}