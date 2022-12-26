package com.example.domain.dto.response

import com.example.domain.dto.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

abstract class Response {
    @Serializable(with = UUIDSerializer::class) abstract val id: UUID
}
