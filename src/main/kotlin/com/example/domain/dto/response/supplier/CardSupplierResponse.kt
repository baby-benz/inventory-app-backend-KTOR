package com.example.domain.dto.response.supplier

import com.example.domain.dto.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CardSupplierResponse(
    @Serializable(with = UUIDSerializer::class)
    override val id: UUID,
    override val name: String
) : SupplierResponse()