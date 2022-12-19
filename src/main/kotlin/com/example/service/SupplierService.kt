package com.example.service

import com.example.dal.DAL
import com.example.domain.dto.request.product.ProductRequest
import com.example.domain.dto.request.supplier.SupplierRequest
import com.example.domain.dto.response.product.ProductResponse
import com.example.domain.dto.response.supplier.SupplierResponse

interface SupplierService : Service<SupplierRequest, SupplierResponse> {
    val productDal: DAL<ProductRequest, ProductResponse>
}