package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.Company

//@formatter:off
enum class ECompany(val company: Company) {
    AUT_BILLA_AG(Company(1, "BILLA AG", "BILLA", EPlace.AUT_NOE_REWE_CENTRAL_OFFICE.place)),
    AUT_DM_GMBH(Company(2, "dm drogerie markt GmbH", "dm", EPlace.AUT_S_DM_CENTRAL_OFFICE.place)),
    AUT_HOFER_KG(Company(3, "HOFER KG", "HOFER", EPlace.AUT_OOE_HOFER_CENTRAL_OFFICE.place)),
    AUT_LIDL_GMBH(Company(4, "LIDL Österreich GmbH", "LIDL", EPlace.AUT_S_LIDL_CENTRAL_OFFICE.place)),
    AUT_MPREIS_GMBH(Company(5, "MPREIS Warenvertriebs-GmbH", "MPreis", EPlace.AUT_T_MPREIS_CENTRAL_OFFICE.place)),
    AUT_MUELLER_HANDELS_GMBH(Company(6, "MHA Müller HandelsgmbH", "Müller", EPlace.AUT_W_MUELLER_CENTRAL_OFFICE.place)),
    AUT_PENNY_GMBH(Company(7, "Penny GmbH", "PENNY", EPlace.AUT_NOE_REWE_CENTRAL_OFFICE.place)),
    AUT_SPAR_WAREN_AG(Company(8, "SPAR Österreichische Warenhandels-AG", "SPAR", EPlace.AUT_S_SPAR_CENTRAL_OFFICE.place)),
    AUT_UNIMARKT_HANDELS_GMBH_CO_KG(Company(9, "UNIMARKT Handels-GmbH & Co. KG", "UNIMARKT", EPlace.AUT_OOE_UNIMARKT_CENTRAL_OFFICE.place)),
    AUT_REWE_GROSSHANDEL_GMBH(Company(10, "REWE Großhandel GmbH", "REWE", EPlace.AUT_NOE_REWE_CENTRAL_OFFICE.place))
}
//@formatter:on
