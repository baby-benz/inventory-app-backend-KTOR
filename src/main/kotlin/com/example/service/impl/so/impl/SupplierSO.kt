package com.example.service.impl.so.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.domain.dto.response.supplier.DefaultSupplierResponse
import com.example.domain.model.Supplier
import com.example.service.impl.so.ServiceObject

data class SupplierSO(override val entity: Supplier) : ServiceObject<DefaultSupplierResponse> {
    override suspend fun toDefaultResponse() = dbQuery {
        DefaultSupplierResponse(entity.id.value, entity.name, entity.phone, entity.email)
    }
}