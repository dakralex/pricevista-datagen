package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.Retailer

//@formatter:off
enum class ERetailer(val retailer: Retailer) {
    ADEG(Retailer(ECompany.AUT_REWE_GROSSHANDEL_GMBH.company, ECurrency.EUR.currency)),
    BILLA(Retailer(ECompany.AUT_BILLA_AG.company, ECurrency.EUR.currency)),
    DM(Retailer(ECompany.AUT_DM_GMBH.company, ECurrency.EUR.currency)),
    HOFER(Retailer(ECompany.AUT_HOFER_KG.company, ECurrency.EUR.currency)),
    LIDL(Retailer(ECompany.AUT_LIDL_GMBH.company, ECurrency.EUR.currency)),
    MPREIS(Retailer(ECompany.AUT_MPREIS_GMBH.company, ECurrency.EUR.currency)),
    MUELLER(Retailer(ECompany.AUT_MUELLER_HANDELS_GMBH.company, ECurrency.EUR.currency)),
    PENNY(Retailer(ECompany.AUT_PENNY_GMBH.company, ECurrency.EUR.currency)),
    SPAR(Retailer(ECompany.AUT_SPAR_WAREN_AG.company, ECurrency.EUR.currency)),
    UNIMARKT(Retailer(ECompany.AUT_UNIMARKT_HANDELS_GMBH_CO_KG.company, ECurrency.EUR.currency)),
}
//@formatter:on
