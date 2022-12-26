package com.example.service.impl.so.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.domain.dto.response.product_category.DefaultProductCategoryResponse
import com.example.domain.model.ProductCategory
import com.example.service.impl.so.ServiceObject

data class ProductCategorySO(override val entity: ProductCategory) : ServiceObject<DefaultProductCategoryResponse> {
    override suspend fun toDefaultResponse() = dbQuery {
        DefaultProductCategoryResponse(entity.id.value, entity.category)
    }
}