package com.example.dal

import java.util.UUID

interface DAL<in U, out T> {
    suspend fun all(): Iterable<T>
    suspend fun find(id: UUID): T?
    suspend fun save(toSave: U): T
    suspend fun save(id: UUID, toSave: U): T
    suspend fun update(id: UUID, toUpdate: U): Boolean
    suspend fun delete(id: UUID): Boolean
    suspend fun existsById(id: UUID): Boolean
}