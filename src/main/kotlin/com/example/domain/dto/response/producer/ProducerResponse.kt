package com.example.domain.dto.response.producer

import com.example.domain.dto.serialization.UUIDSerializer
import com.example.domain.models.Producer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ProducerResponse(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val name: String,
    val productCategories: List<@Serializable(with = UUIDSerializer::class) UUID>
) {
    constructor(producer: Producer) : this(
        producer.id.value,
        producer.name,
        producer.productCategories.map { it.id.value })
}