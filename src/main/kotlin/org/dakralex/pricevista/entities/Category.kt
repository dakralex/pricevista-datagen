package org.dakralex.pricevista.entities

import org.dakralex.pricevista.contracts.entities.Entity

typealias CategoryId = Int

/**
 * The [Category] entity describes a category that articles can belong to.
 */
data class Category(
    val id: CategoryId? = null,

    /** Name of the category **/
    var name: String,

    /** Description of the category **/
    var description: String? = null
) : Entity