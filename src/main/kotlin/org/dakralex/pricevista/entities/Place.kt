package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import org.dakralex.pricevista.database.EntityComp

typealias PlaceId = Int

/**
 * The [Place] entity describes a location in the physical world
 */
data class Place(
    val id: PlaceId? = null,

    /** Country of the place **/
    var country: Country,

    /** Administrative area of the place (e.g. region, county, state) **/
    var adminArea: String? = null,

    /** Locality of the place (e.g. city, municipality, town) **/
    var locality: String? = null,

    /** Postal code of the place **/
    var postalCode: String? = null,

    /** Street address line of the place **/
    var streetAddress: String? = null
) : Entity {
    companion object : EntityComp<Place> {
        override val tableName: String = "Place"
        override val insertStatement: String = """
                insert into $tableName (id, country_id, admin_area, locality, postal_code, street_address)
                values (:id, :countryId, :adminArea, :locality, :postalCode, :streetAddress)
            """.trimIndent()

        override fun insertBatch(db: Database, entries: List<Place>) {
            db.updateBatch(insertStatement, entries.map { entry ->
                arrayOf(
                    entry.id,
                    entry.country.id,
                    entry.adminArea,
                    entry.locality,
                    entry.postalCode,
                    entry.streetAddress
                )
            })
        }
    }

    override fun insert(db: Database) {
        db.update(
            insertStatement,
            id,
            country.id,
            adminArea,
            locality,
            postalCode,
            streetAddress
        )
    }
}
