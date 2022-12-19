package com.example.domain.dto.response

import com.example.domain.dto.serialization.UUIDSerializer
import com.example.domain.models.Product
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ProductResponse(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val name: String,
    val price: Double,
    @Serializable(with = UUIDSerializer::class) val producerId: UUID?,
    @Serializable(with = UUIDSerializer::class) val supplierId: UUID?
) {
    constructor(product: Product) : this(
        product.id.value,
        product.name,
        product.price,
        product.producer?.id?.value,
        product.supplier?.id?.value
    )
}