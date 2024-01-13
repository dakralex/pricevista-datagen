package org.dakralex.pricevista.contracts.dao

import org.dakralex.pricevista.entities.CurrentPrice
import org.dakralex.pricevista.entities.CurrentPriceKey

interface CurrentPriceDao : EntityDao<CurrentPrice, CurrentPriceKey>