package com.example.service.impl

import com.example.dal.ProducerDAL
import com.example.dal.ProductCategoryDAL
import com.example.dal.ProductDAL
import com.example.dal.impl.DefaultProducerDAL
import com.example.dal.impl.DefaultProductCategoryDAL
import com.example.dal.impl.DefaultProductDAL
import com.example.domain.dto.request.producer.ProducerRequest
import com.example.domain.dto.response.producer.CardProducerResponse
import com.example.domain.dto.response.producer.DefaultProducerResponse
import com.example.service.ProducerService
import com.example.service.impl.exceptions.BadReferenceException
import com.example.service.impl.exceptions.NotFoundException
import com.example.service.impl.exceptions.ReferenceViolationException
import java.util.*

class DefaultProducerService(override val dal: ProducerDAL = DefaultProducerDAL()) : ProducerService {
    private val productCategoryDal: ProductCategoryDAL = DefaultProductCategoryDAL()
    private val productDal: ProductDAL = DefaultProductDAL()

    override suspend fun save(toSave: ProducerRequest): DefaultProducerResponse {
        checkReference(toSave)
        return super.save(toSave)
    }

    override suspend fun delete(id: UUID) {
        if (productDal.existsByProducer(id)) throw ReferenceViolationException(id.toString(), "Product", "Producer")
        super.delete(id)
    }

    override suspend fun update(id: UUID, toUpdate: ProducerRequest): DefaultProducerResponse {
        checkReference(toUpdate)
        return if (dal.update(id, toUpdate)) {
            DefaultProducerResponse(id, toUpdate.name, toUpdate.productCategoryIds)
        } else {
            dal.save(id, toUpdate).toDefaultResponse()
        }
    }

    private suspend fun checkReference(producer: ProducerRequest) {
        val productCategoriesInDbSize = productCategoryDal.inListEntitiesSize(producer.productCategoryIds)
        if (productCategoriesInDbSize != producer.productCategoryIds.size.toLong()) {
            throw BadReferenceException()
        }
    }

    override suspend fun allCard(): Collection<CardProducerResponse> {
        return dal.all().map { it.toCardResponse() }
    }

    override suspend fun getCard(id: UUID): CardProducerResponse {
        return dal.find(id)?.toCardResponse() ?: throw NotFoundException(id.toString())
    }
}