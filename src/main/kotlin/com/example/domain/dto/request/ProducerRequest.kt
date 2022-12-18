package com.example.domain.dto.request

import com.example.domain.dto.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ProducerRequest(
    val name: String,
    val productCategoryIds: List<@Serializable(with = UUIDSerializer::class) UUID>
)