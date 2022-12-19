package com.example.service

import com.example.dal.DAL
import com.example.domain.dto.request.producer.ProducerRequest
import com.example.domain.dto.request.product.ProductRequest
import com.example.domain.dto.request.supplier.SupplierRequest
import com.example.domain.dto.response.producer.ProducerResponse
import com.example.domain.dto.response.product.ProductResponse
import com.example.domain.dto.response.supplier.SupplierResponse

interface ProductService : Service<ProductRequest, ProductResponse> {
    val producerDal: DAL<ProducerRequest, ProducerResponse>
    val supplierDal: DAL<SupplierRequest, SupplierResponse>
}