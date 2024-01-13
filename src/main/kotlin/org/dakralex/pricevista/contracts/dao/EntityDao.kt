package org.dakralex.pricevista.contracts.dao

import org.dakralex.pricevista.contracts.entities.Entity

interface EntityDao<E : Entity, N : Any> {
    fun add(entity: E): Boolean

    fun addBatch(entities: Sequence<E>)

    fun findById(id: N): E?

    fun list(): Sequence<E>

    fun commit(): EntityDao<E, N>
}