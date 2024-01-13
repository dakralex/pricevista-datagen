package org.dakralex.pricevista.contracts.dao

import org.dakralex.pricevista.contracts.entities.Entity

interface EntityDao<E : Entity, N : Any> {
    fun add(entity: E): Boolean

    fun addBatch(entities: Iterable<E>)

    fun addBatch(entities: Sequence<E>)

    fun findById(id: N): E?

    fun commit(): Boolean
}