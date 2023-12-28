package org.dakralex.pricevista.datagen.entities

interface HasLocation {
    var country: String
    var adminArea: String?
    var locality: String?
}

interface HasExactLocation : HasLocation {
    var postalCode: String?
    var streetAddress: String?
}
