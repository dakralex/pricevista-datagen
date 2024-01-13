package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity
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
    var articleUnit: ArticleUnit,

    /** Quantity of the article in the measurement unit **/
    var quantity: BigDecimal,

    /** Whether the article can be bought in bulk by weighing it */
    var weightable: Boolean
) : Entity