package org.dakralex.pricevista.contracts.dao

import org.dakralex.pricevista.entities.RecordedPrice
import org.dakralex.pricevista.entities.RecordedPriceKey

interface RecordedPriceDao : EntityDao<RecordedPrice, RecordedPriceKey>