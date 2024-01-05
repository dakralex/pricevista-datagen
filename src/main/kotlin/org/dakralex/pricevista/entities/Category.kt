package org.dakralex.pricevista.entities

/**
 * The [Category] entity describes a category that articles can belong to.
 */
data class Category(
    val id: Int? = null,

    /** Name of the category **/
    var name: String,

    /** Description of the category **/
    var description: String? = null
)
