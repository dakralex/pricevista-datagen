package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.Entity

/**
 * The [Store] entity describes a place where a retailer distributes articles
 * by advertising, stocking, and selling them over that platform. This could
 * be a physical place (e.g. a shop) or a virtual place (e.g. online store).
 *
 * In the case that this store is an online store, the location should at least
 * define the country the online store is intended for.
 */
data class Store(
    val id: Int? = null,

    /** Retailer that runs the store **/
    val retailer: Retailer,

    /** Location of the store **/
    var place: Place,

    /** Currency of the store **/
    var currency: Currency,

    /** Language of the store **/
    var language: Language
) : Entity {
    override val tableName = "Store"
    override val insertStatement = """
        insert into $tableName (id, retailer_id, place_id, currency_id, language_id)
        values (:id, :retailerId, :placeId, :currencyId, :languageId)
    """.trimIndent()

    override fun insert(db: Database) {
        db.update(
            insertStatement,
            id,
            retailer.company.id,
            place.id,
            currency.id,
            language.id
        )
    }
}
