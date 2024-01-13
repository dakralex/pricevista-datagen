package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias ArticleUnitId = Int

/**
 * The [ArticleUnit] entity describes a measurement unit that is used by
 * stores to quantify their articles, packages and volume sizes.
 */
data class ArticleUnit(
    val id: ArticleUnitId,

    /** Symbol or label of the measurement unit **/
    val label: String,

    /** English name of the measurement unit in singular **/
    val singularName: String = label,

    /** English name of the measurement unit in plural **/
    val pluralName: String = singularName + "s",
) : Entity