package com.example.domain.dto.response

import com.example.domain.dto.request.ProductCategoryRequest
import com.example.domain.dto.serialization.UUIDSerializer
import com.example.domain.models.ProductCategory
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ProductCategoryResponse(@Serializable(with = UUIDSerializer::class) val id: UUID, val category: String) {
    constructor(category: ProductCategory) : this(category.id.value, category.category)
    constructor(id: UUID, category: ProductCategoryRequest) : this(id, category.category)
}