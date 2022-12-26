package com.example.service.impl

import com.example.dal.ProductCategoryDAL
import com.example.dal.impl.DefaultProductCategoryDAL
import com.example.domain.dto.request.product_category.ProductCategoryRequest
import com.example.domain.dto.response.product_category.DefaultProductCategoryResponse
import com.example.service.ProductCategoryService
import com.example.service.impl.exceptions.DuplicateCategoryException
import java.util.*

class DefaultProductCategoryService(override val dal: ProductCategoryDAL = DefaultProductCategoryDAL()) : ProductCategoryService {
    override suspend fun save(toSave: ProductCategoryRequest): DefaultProductCategoryResponse {
        return if (!dal.existsByCategory(toSave.category)) {
            super.save(toSave)
        } else {
            throw DuplicateCategoryException(toSave.category)
        }
    }

    override suspend fun update(id: UUID, toUpdate: ProductCategoryRequest): DefaultProductCategoryResponse {
        return if (dal.update(id, toUpdate)) {
            DefaultProductCategoryResponse(id, toUpdate.category)
        } else {
            dal.save(id, toUpdate).toDefaultResponse()
        }
    }
}