package com.example.service.impl

import com.example.dal.SupplierDAL
import com.example.dal.impl.DefaultSupplierDAL
import com.example.domain.dto.request.SupplierRequest
import com.example.domain.dto.response.SupplierResponse
import com.example.service.SupplierService
import java.util.*

class DefaultSupplierService : SupplierService {
    override val dal: SupplierDAL = DefaultSupplierDAL()

    override suspend fun update(id: UUID, toUpdate: SupplierRequest): SupplierResponse {
        return if (dal.update(id, toUpdate)) {
            SupplierResponse(id, name = toUpdate.name, phone = toUpdate.phone, email = toUpdate.email)
        } else {
            dal.save(id, toUpdate)
        }
    }
}