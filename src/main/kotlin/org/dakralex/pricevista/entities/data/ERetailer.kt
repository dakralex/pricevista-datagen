package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.Retailer

//@formatter:off
enum class ERetailer(val retailer: Retailer) {
    ADEG(Retailer(ECompany.AUT_REWE_GROSSHANDEL_GMBH.company)),
    BILLA(Retailer(ECompany.AUT_BILLA_AG.company)),
    DM(Retailer(ECompany.AUT_DM_GMBH.company)),
    HOFER(Retailer(ECompany.AUT_HOFER_KG.company)),
    LIDL(Retailer(ECompany.AUT_LIDL_GMBH.company)),
    MPREIS(Retailer(ECompany.AUT_MPREIS_GMBH.company)),
    MUELLER(Retailer(ECompany.AUT_MUELLER_HANDELS_GMBH.company)),
    PENNY(Retailer(ECompany.AUT_PENNY_GMBH.company)),
    SPAR(Retailer(ECompany.AUT_SPAR_WAREN_AG.company)),
    UNIMARKT(Retailer(ECompany.AUT_UNIMARKT_HANDELS_GMBH_CO_KG.company)),
}
//@formatter:on
