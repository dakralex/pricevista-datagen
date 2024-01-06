package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [Place] entity describes a location in the physical world
 */
data class Place(
    val id: Int? = null,

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
    override val tableName = "Place"
    override val insertStatement = """
        insert into $tableName (id, country_id, admin_area, locality, postal_code, street_address)
        values (:id, :countryId, :adminArea, :locality, :postalCode, :streetAddress)
    """.trimIndent()

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
