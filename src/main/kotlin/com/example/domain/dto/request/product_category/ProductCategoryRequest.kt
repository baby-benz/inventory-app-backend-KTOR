package com.example.domain.dto.request.product_category

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategoryRequest(val category: String)