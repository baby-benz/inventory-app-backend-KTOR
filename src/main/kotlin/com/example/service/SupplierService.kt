package com.example.service

import com.example.dal.DAL
import com.example.domain.dto.request.ProductRequest
import com.example.domain.dto.request.SupplierRequest
import com.example.domain.dto.response.ProductResponse
import com.example.domain.dto.response.SupplierResponse

interface SupplierService : Service<SupplierRequest, SupplierResponse> {
    val productDal: DAL<ProductRequest, ProductResponse>
}