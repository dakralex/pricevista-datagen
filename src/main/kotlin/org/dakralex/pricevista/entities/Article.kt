package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity
import kotlin.math.abs

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
    var articleUnit: ArticleUnit,

    /** Quantity of the article in the measurement unit **/
    var quantity: Double,

    /** Whether the article can be bought in bulk by weighing it */
    var weightable: Boolean
) : Entity {

    companion object {
        const val QUANTITY_EPSILON = 0.01
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        if (brand != other.brand) return false
        if (!name.equals(other.name, true)) return false
        if (articleUnit != other.articleUnit) return false
        if (abs(quantity - other.quantity) > QUANTITY_EPSILON) return false
        if (weightable != other.weightable) return false

        return true
    }

    override fun hashCode(): Int {
        var result = brand?.hashCode() ?: 0
        result = 31 * result + name.lowercase().hashCode()
        result = 31 * result + articleUnit.hashCode()
        result = 31 * result + quantity.hashCode()
        result = 31 * result + weightable.hashCode()
        return result
    }

    override fun toString(): String {
        val brandName = brand?.company?.shortName ?: ""

        return "Article(\n" +
                "id=$id,\n" +
                "brandName=$brandName,\n" +
                "name='$name',\n" +
                "description=$description,\n" +
                "originCountry=$originCountry,\n" +
                "articleUnit=$articleUnit,\n" +
                "quantity=$quantity,\n" +
                "weightable=$weightable\n" +
                ")"
    }
}