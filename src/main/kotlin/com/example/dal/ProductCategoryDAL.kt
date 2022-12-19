package com.example.dal

import com.example.domain.dto.request.product_category.ProductCategoryRequest
import com.example.domain.dto.response.product_category.ProductCategoryResponse
import java.util.*

interface ProductCategoryDAL : DAL<ProductCategoryRequest, ProductCategoryResponse> {
    suspend fun existsByCategory(category: String): Boolean
    suspend fun inListEntitiesSize(ids: Collection<UUID>): Long
}