package com.example.service

import com.example.dal.DAL
import com.example.domain.dto.request.ProducerRequest
import com.example.domain.dto.request.ProductRequest
import com.example.domain.dto.request.SupplierRequest
import com.example.domain.dto.response.ProducerResponse
import com.example.domain.dto.response.ProductResponse
import com.example.domain.dto.response.SupplierResponse

interface ProductService : Service<ProductRequest, ProductResponse> {
    val producerDal: DAL<ProducerRequest, ProducerResponse>
    val supplierDal: DAL<SupplierRequest, SupplierResponse>
}