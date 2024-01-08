package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity
import java.math.BigDecimal

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

    /** Country where the article originated from **/
    var originCountry: Country? = null,

    /** Measurement unit of the article **/
    var unit: MeasurementUnit,

    /** Quantity of the article in the measurement unit **/
    var quantity: BigDecimal,

    /** Whether the article can be bought in bulk by weighing it */
    var weightable: Boolean
) : Entity {
    companion object {
        const val tableName: String = "Article"
        const val insertStatement: String =
            """insert into $tableName (id, brand_id, name, description, origin_country_id, unit_id, quantity, weightable) values (:id, :brandId, :name, :description, :originCountryId, :unitId, :quantity, :weightable)"""
    }

    override fun insert(db: Database) {
        db.update(
            insertStatement,
            id,
            brand?.company?.id,
            name,
            description,
            originCountry?.id,
            unit.id,
            quantity,
            weightable
        )
    }
}
