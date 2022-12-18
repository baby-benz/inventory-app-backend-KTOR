package com.example.domain.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategoryRequest(val category: String)