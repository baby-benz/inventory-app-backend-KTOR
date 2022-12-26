package com.example.dal

import com.example.domain.dto.request.product.ProductRequest
import com.example.service.impl.so.impl.ProductSO
import java.util.*

interface ProductDAL : DAL<ProductRequest, ProductSO> {
    suspend fun existsByProducer(producerId: UUID): Boolean
    suspend fun existsBySupplier(supplierId: UUID): Boolean
}