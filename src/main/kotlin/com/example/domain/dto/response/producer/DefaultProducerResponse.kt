package com.example.domain.dto.response.producer

import com.example.domain.dto.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class DefaultProducerResponse(
    @Serializable(with = UUIDSerializer::class) override val id: UUID,
    override val name: String,
    val productCategories: List<@Serializable(with = UUIDSerializer::class) UUID>
) : ProducerResponse()