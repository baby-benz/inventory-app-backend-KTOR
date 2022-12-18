package com.example.service

import com.example.dal.DAL
import com.example.service.impl.exceptions.NotFoundException
import java.util.*

interface Service<in U, T> {
    val dal: DAL<U, T>

    suspend fun all(): Collection<T> {
        return dal.all().toList()
    }

    suspend fun get(id: UUID): T {
        return dal.find(id) ?: throw NotFoundException(id.toString())
    }

    suspend fun save(toSave: U): T {
        return dal.save(toSave)
    }

    suspend fun delete(id: UUID) {
        if (!dal.delete(id)) throw NotFoundException(id.toString())
    }

    suspend fun update(id: UUID, toUpdate: U): T
}