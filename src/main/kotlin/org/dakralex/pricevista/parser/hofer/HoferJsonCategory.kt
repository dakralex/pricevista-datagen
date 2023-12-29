package org.dakralex.pricevista.parser.hofer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoferJsonCategory(
    @SerialName("CategoryProviderImage")
    val categoryProviderImage: String? = null,
    @SerialName("alternativeCategoryProviderID")
    val alternativeCategoryProviderId: String? = null,
    @SerialName("brandProviderID")
    val brandProviderId: Int,
    @SerialName("categoryID")
    val categoryId: Int,
    val categoryIconFileName: String? = null,
    val categoryImageFileName: String? = null,
    val categoryName: String,
    val childList: List<String>,
    val isRoot: Boolean,
    val level: Int,
    val parentCategory: String? = null,
    @SerialName("progID")
    val progId: String,
    val url: String
)