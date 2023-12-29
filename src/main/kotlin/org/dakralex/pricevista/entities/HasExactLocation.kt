package org.dakralex.pricevista.entities

interface HasExactLocation : HasLocation {
    var postalCode: String?
    var streetAddress: String?
}
