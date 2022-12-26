package com.example.domain.dto.response.product_category

import com.example.domain.dto.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class DefaultProductCategoryResponse(
    @Serializable(with = UUIDSerializer::class) override val id: UUID,
    override val category: String
) : ProductCategoryResponse()