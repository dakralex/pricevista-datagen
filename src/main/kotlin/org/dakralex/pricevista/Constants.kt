package org.dakralex.pricevista

import java.math.MathContext
import java.math.RoundingMode

const val CONFIG_FILE = "datagen.properties"
const val VERSION_FILE = "version.properties"

val PRICE_MATH_CONTEXT = MathContext(4, RoundingMode.HALF_UP)