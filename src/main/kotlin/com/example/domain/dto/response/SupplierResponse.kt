package com.example.domain.dto.response

import com.example.domain.dto.serialization.UUIDSerializer
import com.example.domain.models.Supplier
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class SupplierResponse(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val name: String,
    val phone: String,
    val email: String
) {
    constructor(supplier: Supplier) : this(supplier.id.value, supplier.name, supplier.phone, supplier.email)
}