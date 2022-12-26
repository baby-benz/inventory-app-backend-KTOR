package com.example.domain.dto.response.product

import com.example.domain.dto.serialization.UUIDSerializer
import com.example.domain.model.Product
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CardProductResponse(
    @Serializable(with = UUIDSerializer::class) override val id: UUID,
    override val name: String,
    override val price: Double,
    val producerName: String,
    val supplierName: String
) : ProductResponse()