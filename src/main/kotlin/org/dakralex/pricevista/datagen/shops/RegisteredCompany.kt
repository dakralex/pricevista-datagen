package org.dakralex.pricevista.datagen.shops

import org.dakralex.pricevista.datagen.entities.Company
import org.dakralex.pricevista.datagen.entities.Retailer

// TODO Refactor this to a cleaner version
enum class RegisteredCompany(val company: Company) {
    AUT_BILLA_AG(
        Company(
            1,
            "Billa Aktiengesellschaft",
            "AUT",
            "Niederösterreich",
            "Wiener Neudorf",
            "2355",
            "Industriezentrum NÖ-Süd, Straße 3, Objekt 16"
        )
    ),
    AUT_DM_GMBH(Company(2, "dm drogerie markt GmbH", "AUT", "Salzburg", "Wals", "5071", "Günter-Bauer-Straße 1")),
    AUT_HOFER_KG(Company(3, "HOFER KG", "AUT", "Oberösterreich", "Sattledt", "4642", "Hofer Straße 1")),
    AUT_LIDL_GMBH(Company(4, "Lidl Österreich GmbH", "AUT", "Salzburg", "Salzburg", "5020", "Unter der Leiten 11")),
    AUT_MPREIS_GMBH(Company(5, "MPREIS Warenvertriebs GmbH", "AUT", "Tirol", "Völs", "6176", "Landesstraße 16")),
    AUT_MUELLER_HANDELS_GMBH(
        Company(
            6,
            "MHA Müller HandelsgmbH",
            "AUT",
            "Wien",
            "Wien",
            "1100",
            "Favoritenstraße 112-114"
        )
    ),
    AUT_PENNY_GMBH(
        Company(
            7,
            "Penny GmbH",
            "AUT",
            "Niederösterreich",
            "Wiener Neudorf",
            "2355",
            "Industriezentrum NÖ-Süd, Straße 3, Objekt 16"
        )
    ),
    AUT_SPAR_WAREN_AG(
        Company(
            8,
            "SPAR Österreichische Warenhandels-Aktiengesellschaft",
            "AUT",
            "Salzburg",
            "Salzburg",
            "5015",
            "Europastraße 3"
        )
    ),
    AUT_UNIMARKT_HANDEL_GMBH_CO_KG(
        Company(
            9,
            "UNIMARKT Handelsgesellschaft m.b.H. & Co. Kommanditgesellschaft",
            "AUT",
            "Oberösterreich",
            "Traun",
            "4050",
            "Egger-Lienz-Straße 14"
        )
    ),
    REWE_GROSSHANDEL_GMBH(
        Company(
            10,
            "REWE Großhandel GmbH",
            "AUT",
            "Niederösterreich",
            "Wiener Neudorf",
            "2355",
            "Industriezentrum NÖ-Süd, Straße 3, Objekt 16"
        )
    )
}

// TODO Refactor this to a cleaner version
enum class RegisteredRetailers(val retailer: Retailer) {
    ADEG(Retailer(RegisteredCompany.REWE_GROSSHANDEL_GMBH.company)),
    BILLA(Retailer(RegisteredCompany.AUT_BILLA_AG.company)),
    DM(Retailer(RegisteredCompany.AUT_DM_GMBH.company)),
    HOFER(Retailer(RegisteredCompany.AUT_HOFER_KG.company)),
    LIDL(Retailer(RegisteredCompany.AUT_LIDL_GMBH.company)),
    MPREIS(Retailer(RegisteredCompany.AUT_MPREIS_GMBH.company)),
    MUELLER(Retailer(RegisteredCompany.AUT_MUELLER_HANDELS_GMBH.company)),
    PENNY(Retailer(RegisteredCompany.AUT_PENNY_GMBH.company)),
    SPAR(Retailer(RegisteredCompany.AUT_SPAR_WAREN_AG.company)),
    UNIMARKT(Retailer(RegisteredCompany.AUT_UNIMARKT_HANDEL_GMBH_CO_KG.company))
}
