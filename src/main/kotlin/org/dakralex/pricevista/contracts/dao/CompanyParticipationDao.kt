package org.dakralex.pricevista.contracts.dao

import org.dakralex.pricevista.entities.CompanyParticipation
import org.dakralex.pricevista.entities.CompanyParticipationKey

interface CompanyParticipationDao :
    EntityDao<CompanyParticipation, CompanyParticipationKey>