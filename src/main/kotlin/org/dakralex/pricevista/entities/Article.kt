package org.dakralex.pricevista.entities

import org.dakralex.pricevista.database.Database
import org.dakralex.pricevista.database.ResolvableEntity
import org.dakralex.pricevista.database.ResolvableEntityComp
import java.math.BigDecimal

typealias ArticleId = Int

/**
 * The [Article] entity describes an article that is available in one or more
 * stores of the retailers with some details.
 */
data class Article(
    var id: ArticleId? = null,

    /** Brand of the article **/
    var brand: Brand? = null,

    /** Name of the article without brand, quantity or unit */
    var name: String,

    /** Description of the article **/
    var description: String? = null,

    /** Country where the article originated from **/
    var originCountry: Country? = null,

    /** Article unit of the article **/
    var unit: ArticleUnit,

    /** Quantity of the article in the measurement unit **/
    var quantity: BigDecimal,

    /** Whether the article can be bought in bulk by weighing it */
    var weightable: Boolean
) : ResolvableEntity {
    companion object : ResolvableEntityComp<Article> {
        override val tableName: String = "Article"
        override val insertStatement: String = """
                insert into $tableName (id, brand_id, name, description, origin_country_id, unit_id, quantity, weightable)
                values (:id, :brandId, :name, :description, :originCountryId, :unitId, :quantity, :weightable)
            """.trimIndent()
        override val selectStatement: String = """
                select id from $tableName
                    where brand_id = :brandId and
                          name = :name and
                          unit_id = :unitId and
                          quantity = :quantity
            """.trimIndent()
    }

    override fun resolveFrom(db: Database) {
        id = db.query(
            selectStatement,
            brand?.company?.id,
            name,
            unit.id,
            quantity
        ) {
            it.getInt("id")
        }.firstOrNull() ?: id
    }

    override fun insert(db: Database) {
        if (id == null) {
            resolveFrom(db)
        }

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
