package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.Serializable

/**
 * This value class represents a price value in a JSON export, where the prices
 * are stored as whole integers to the smallest money unit (e.g. Euro cents).
 */
@JvmInline
@Serializable
value class BillaJsonPrice(val value: Int)
