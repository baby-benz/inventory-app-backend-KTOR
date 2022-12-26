package com.example.service.impl.so

import com.example.domain.dto.response.Response
import org.jetbrains.exposed.dao.UUIDEntity

interface ServiceObject<out R: Response> {
    val entity: UUIDEntity

    suspend fun toDefaultResponse(): R
}