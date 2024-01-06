package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.Store

//@formatter:off
enum class EStore(val store: Store) {
    BILLA(Store(1, ERetailer.BILLA.retailer, EPlace.AUT_GENERAL.place, ECurrency.EUR.currency, ELanguage.DE.language)),
    HOFER(Store(1, ERetailer.HOFER.retailer, EPlace.AUT_GENERAL.place, ECurrency.EUR.currency, ELanguage.DE.language)),
    SPAR(Store(1, ERetailer.SPAR.retailer, EPlace.AUT_GENERAL.place, ECurrency.EUR.currency, ELanguage.DE.language)),
}
//@formatter:on
