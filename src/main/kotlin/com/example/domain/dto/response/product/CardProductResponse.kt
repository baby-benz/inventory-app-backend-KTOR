package com.example.domain.dto.response.product

import com.example.domain.dto.response.producer.CardProducerResponse
import com.example.domain.dto.response.supplier.CardSupplierResponse
import com.example.domain.dto.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CardProductResponse(
    @Serializable(with = UUIDSerializer::class) override val id: UUID,
    override val name: String,
    override val price: Double,
    val producerName: CardProducerResponse,
    val supplierName: CardSupplierResponse
) : ProductResponse()