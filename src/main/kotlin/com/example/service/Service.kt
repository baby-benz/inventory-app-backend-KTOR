package com.example.service

import com.example.dal.DAL
import com.example.domain.dto.response.Response
import com.example.service.impl.exceptions.NotFoundException
import com.example.service.impl.so.ServiceObject
import java.util.*

interface Service<in U, out T : Response> {
    val dal: DAL<U, ServiceObject<T>>

    suspend fun all(): Collection<T> {
        return dal.all().map { it.toDefaultResponse() }.toList()
    }

    suspend fun get(id: UUID): T {
        return dal.find(id)?.toDefaultResponse() ?: throw NotFoundException(id.toString())
    }

    suspend fun save(toSave: U): T {
        return dal.save(toSave).toDefaultResponse()
    }

    suspend fun delete(id: UUID) {
        if (!dal.delete(id)) throw NotFoundException(id.toString())
    }

    suspend fun update(id: UUID, toUpdate: U): T
}