package com.example.service.impl

import com.example.dal.ProducerDAL
import com.example.dal.ProductDAL
import com.example.dal.SupplierDAL
import com.example.dal.impl.DefaultProducerDAL
import com.example.dal.impl.DefaultProductDAL
import com.example.dal.impl.DefaultSupplierDAL
import com.example.domain.dto.request.ProductRequest
import com.example.domain.dto.response.ProductResponse
import com.example.domain.dto.response.SupplierResponse
import com.example.service.ProductService
import com.example.service.impl.exceptions.BadReferenceException
import java.util.*

class DefaultProductService : ProductService {
    override val dal: ProductDAL = DefaultProductDAL()
    override val producerDal: ProducerDAL = DefaultProducerDAL()
    override val supplierDal: SupplierDAL = DefaultSupplierDAL()

    override suspend fun save(toSave: ProductRequest): ProductResponse {
        checkReference(toSave)
        return dal.save(toSave)
    }

    override suspend fun update(id: UUID, toUpdate: ProductRequest): ProductResponse {
        checkReference(toUpdate)
        return if (dal.update(id, toUpdate)) {
            ProductResponse(id, toUpdate.name, toUpdate.price, toUpdate.producerId, toUpdate.supplierId)
        } else {
            dal.save(id, toUpdate)
        }
    }

    private suspend fun checkReference(product: ProductRequest) {
        if (!producerDal.existsById(product.producerId)) {
            throw BadReferenceException(product.producerId.toString())
        } else if(!supplierDal.existsById(product.supplierId)) {
            throw BadReferenceException(product.producerId.toString())
        }
    }
}