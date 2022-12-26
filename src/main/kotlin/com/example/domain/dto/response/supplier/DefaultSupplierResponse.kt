package com.example.domain.dto.response.supplier

import com.example.domain.dto.serialization.UUIDSerializer
import com.example.domain.model.Supplier
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class DefaultSupplierResponse(
    @Serializable(with = UUIDSerializer::class) override val id: UUID,
    override val name: String,
    override val phone: String,
    override val email: String
) : SupplierResponse()