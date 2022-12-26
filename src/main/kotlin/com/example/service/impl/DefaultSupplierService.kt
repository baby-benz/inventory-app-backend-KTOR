package com.example.service.impl

import com.example.dal.ProductDAL
import com.example.dal.SupplierDAL
import com.example.dal.impl.DefaultProductDAL
import com.example.dal.impl.DefaultSupplierDAL
import com.example.domain.dto.request.supplier.SupplierRequest
import com.example.domain.dto.response.supplier.DefaultSupplierResponse
import com.example.service.SupplierService
import com.example.service.impl.exceptions.ReferenceViolationException
import java.util.*

class DefaultSupplierService(override val dal: SupplierDAL = DefaultSupplierDAL()) : SupplierService {
    private val productDal: ProductDAL = DefaultProductDAL()

    override suspend fun delete(id: UUID) {
        if (productDal.existsBySupplier(id)) throw ReferenceViolationException(id.toString(), "Product", "Supplier")
        super.delete(id)
    }

    override suspend fun update(id: UUID, toUpdate: SupplierRequest): DefaultSupplierResponse {
        return if (dal.update(id, toUpdate)) {
            DefaultSupplierResponse(id, toUpdate.name, toUpdate.phone, toUpdate.email)
        } else {
            dal.save(id, toUpdate).toDefaultResponse()
        }
    }
}