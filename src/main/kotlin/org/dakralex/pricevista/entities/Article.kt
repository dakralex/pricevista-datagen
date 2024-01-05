package org.dakralex.pricevista.entities

/**
 * The [Article] entity describes an article that is available in one or more
 * stores of the retailers with some details.
 */
data class Article(
    val id: Int? = null,

    /** Brand of the article **/
    var brand: Brand? = null,

    /** Name of the article without brand, quantity or unit */
    var name: String,

    /** Description of the article **/
    var description: String? = null,

    /** Location where the article was produced at or otherwise originated from **/
    var origin: Place? = null,

    /** Measurement unit of the article **/
    var unit: MeasurementUnit,

    /** Quantity of the article in the measurement unit **/
    var quantity: Double,

    /** Whether the article can be bought in bulk by weighing it */
    var weightable: Boolean
)
