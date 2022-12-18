package com.example.service.impl

import com.example.dal.ProductCategoryDAL
import com.example.dal.impl.DefaultProductCategoryDAL
import com.example.domain.dto.request.ProductCategoryRequest
import com.example.domain.dto.response.ProducerResponse
import com.example.domain.dto.response.ProductCategoryResponse
import com.example.service.ProductCategoryService
import com.example.service.impl.exceptions.DuplicateCategoryException
import java.util.*

class DefaultProductCategoryService : ProductCategoryService {
    override val dal: ProductCategoryDAL = DefaultProductCategoryDAL()

    override suspend fun save(toSave: ProductCategoryRequest): ProductCategoryResponse {
        return if (!dal.existsByCategory(toSave.category)) {
            dal.save(toSave)
        } else {
            throw DuplicateCategoryException(toSave.category)
        }
    }

    override suspend fun update(id: UUID, toUpdate: ProductCategoryRequest): ProductCategoryResponse {
        return if (dal.update(id, toUpdate)) {
            ProductCategoryResponse(id, toUpdate.category)
        } else {
            dal.save(id, toUpdate)
        }
    }
}