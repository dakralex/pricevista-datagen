package org.dakralex.pricevista.entities

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
)
