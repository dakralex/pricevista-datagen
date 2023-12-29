package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BillaJsonPriceTag {
    @SerialName("pt-aktion")
    AKTION,
    @SerialName("pt-satterrabatt")
    SATTERRABATT,
    @SerialName("pt-multi")
    MULTI,
    @SerialName("pt-abverkauf")
    ABVERKAUF,
    @SerialName("pt-kurant")
    KURANT,
    @SerialName("pt-1plus1")
    EINSPLUSEINS,
    @SerialName("pt-2plus1")
    ZWEIPLUSEINS,
    @SerialName("pt-3plus1")
    DREIPLUSEINS,
    @SerialName("pt-4plus2")
    VIERPLUSZWEI,
    @SerialName("pt-4plus4")
    VIERPLUSVIER,
    @SerialName("pt-12plus12")
    ZWOELFPLUSZWOELF,
    @SerialName("pt-5plus3")
    FUENFPLUSDREI,
    @SerialName("pt-8plus4")
    ACHTPLUSVIER
}