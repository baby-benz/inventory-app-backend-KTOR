package com.example.service.impl

import com.example.dal.ProducerDAL
import com.example.dal.ProductDAL
import com.example.dal.SupplierDAL
import com.example.dal.impl.DefaultProducerDAL
import com.example.dal.impl.DefaultProductDAL
import com.example.dal.impl.DefaultSupplierDAL
import com.example.domain.dto.request.product.ProductRequest
import com.example.domain.dto.response.product.CardProductResponse
import com.example.domain.dto.response.product.DefaultProductResponse
import com.example.service.ProductService
import com.example.service.impl.exceptions.BadReferenceException
import com.example.service.impl.exceptions.NotFoundException
import java.util.*

class DefaultProductService(override val dal: ProductDAL = DefaultProductDAL()) : ProductService {
    private val producerDal: ProducerDAL = DefaultProducerDAL()
    private val supplierDal: SupplierDAL = DefaultSupplierDAL()

    override suspend fun allCard(): Collection<CardProductResponse> {
        return dal.all().map { it.toCardResponse() }
    }

    override suspend fun getCard(id: UUID): CardProductResponse {
        return dal.find(id)?.toCardResponse() ?: throw NotFoundException(id.toString())
    }

    override suspend fun save(toSave: ProductRequest): DefaultProductResponse {
        checkReference(toSave)
        return super.save(toSave)
    }

    override suspend fun update(id: UUID, toUpdate: ProductRequest): DefaultProductResponse {
        checkReference(toUpdate)
        return if (dal.update(id, toUpdate)) {
            DefaultProductResponse(id, toUpdate.name, toUpdate.price, toUpdate.producerId, toUpdate.supplierId)
        } else {
            dal.save(id, toUpdate).toDefaultResponse()
        }
    }

    private suspend fun checkReference(product: ProductRequest) {
        if (!producerDal.existsById(product.producerId)) {
            throw BadReferenceException(product.producerId.toString())
        } else if (!supplierDal.existsById(product.supplierId)) {
            throw BadReferenceException(product.producerId.toString())
        }
    }
}