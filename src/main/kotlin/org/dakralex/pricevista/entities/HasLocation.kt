package org.dakralex.pricevista.entities

interface HasLocation {
    var country: String
    var adminArea: String?
    var locality: String?
}