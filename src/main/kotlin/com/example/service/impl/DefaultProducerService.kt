package com.example.service.impl

import com.example.dal.ProducerDAL
import com.example.dal.ProductCategoryDAL
import com.example.dal.impl.DefaultProducerDAL
import com.example.dal.impl.DefaultProductCategoryDAL
import com.example.domain.dto.request.ProducerRequest
import com.example.domain.dto.response.ProducerResponse
import com.example.service.ProducerService
import com.example.service.impl.exceptions.BadReferenceException
import java.util.*

class DefaultProducerService : ProducerService {
    override val dal: ProducerDAL = DefaultProducerDAL()
    override val productCategoryDal: ProductCategoryDAL = DefaultProductCategoryDAL()

    override suspend fun save(toSave: ProducerRequest): ProducerResponse {
        checkReference(toSave)
        return dal.save(toSave)
    }

    override suspend fun update(id: UUID, toUpdate: ProducerRequest): ProducerResponse {
        checkReference(toUpdate)
        return if (dal.update(id, toUpdate)) {
            ProducerResponse(id, toUpdate.name, toUpdate.productCategoryIds)
        } else {
            dal.save(id, toUpdate)
        }
    }

    private suspend fun checkReference(producer: ProducerRequest) {
        val productCategoriesInDbSize = productCategoryDal.inListEntitiesSize(producer.productCategoryIds)
        if (productCategoriesInDbSize != producer.productCategoryIds.size.toLong()) {
            throw BadReferenceException()
        }
    }
}