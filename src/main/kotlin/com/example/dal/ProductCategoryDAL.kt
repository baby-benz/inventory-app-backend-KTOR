package com.example.dal

import com.example.domain.dto.request.product_category.ProductCategoryRequest
import com.example.service.impl.so.impl.ProductCategorySO
import java.util.*

interface ProductCategoryDAL : DAL<ProductCategoryRequest, ProductCategorySO> {
    suspend fun existsByCategory(category: String): Boolean
    suspend fun inListEntitiesSize(ids: Collection<UUID>): Long
}