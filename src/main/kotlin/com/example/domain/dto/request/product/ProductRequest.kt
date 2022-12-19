package com.example.domain.dto.request.product

import com.example.domain.dto.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ProductRequest(
    val name: String,
    val price: Double,
    @Serializable(with = UUIDSerializer::class) val producerId: UUID,
    @Serializable(with = UUIDSerializer::class) val supplierId: UUID
)