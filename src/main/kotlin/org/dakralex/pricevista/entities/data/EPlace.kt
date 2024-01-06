package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.Place

//@formatter:off
enum class EPlace(val place: Place) {
    AUT_GENERAL(Place(1, ECountry.AT.country)),
    AUT_NOE_REWE_CENTRAL_OFFICE(Place(2, ECountry.AT.country, "Niederösterreich", "Wiener Neudorf", "2355", "Industriezentrum NÖ-Süd, Straße 3, Objekt 16")),
    AUT_S_DM_CENTRAL_OFFICE(Place(3, ECountry.AT.country, "Salzburg", "Wals", "5071", "Günter-Bauer-Straße 1")),
    AUT_OOE_HOFER_CENTRAL_OFFICE(Place(4, ECountry.AT.country, "Oberösterreich", "Sattledt", "4642", "Hofer Straße 1")),
    AUT_S_LIDL_CENTRAL_OFFICE(Place(5, ECountry.AT.country, "Salzburg", "Salzburg", "5020", "Unter der Leiten 11")),
    AUT_T_MPREIS_CENTRAL_OFFICE(Place(6, ECountry.AT.country, "Tirol", "Völs", "6176", "Landesstraße 16")),
    AUT_W_MUELLER_CENTRAL_OFFICE(Place(7, ECountry.AT.country, "Wien", "Wien", "1100", "Favoritenstraße 112-114")),
    AUT_S_SPAR_CENTRAL_OFFICE(Place(8, ECountry.AT.country, "Salzburg", "Salzburg", "5015", "Europastraße 3")),
    AUT_OOE_UNIMARKT_CENTRAL_OFFICE(Place(9, ECountry.AT.country, "Oberösterreich", "Traun", "4050", "Egger-Lienz-Straße 14")),
}
//@formatter:on
