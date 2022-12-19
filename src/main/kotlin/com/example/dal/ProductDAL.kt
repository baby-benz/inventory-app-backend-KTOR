package com.example.dal

import com.example.domain.dto.request.ProductRequest
import com.example.domain.dto.response.ProductResponse
import java.util.*

interface ProductDAL : DAL<ProductRequest, ProductResponse> {
    suspend fun existsByProducer(producerId: UUID): Boolean
    suspend fun existsBySupplier(supplierId: UUID): Boolean
}