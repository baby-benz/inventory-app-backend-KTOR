package com.example.service

import com.example.dal.DAL
import com.example.domain.dto.request.producer.ProducerRequest
import com.example.domain.dto.request.product_category.ProductCategoryRequest
import com.example.domain.dto.request.product.ProductRequest
import com.example.domain.dto.response.producer.ProducerResponse
import com.example.domain.dto.response.product_category.ProductCategoryResponse
import com.example.domain.dto.response.product.ProductResponse

interface ProducerService : Service<ProducerRequest, ProducerResponse> {
    val productCategoryDal: DAL<ProductCategoryRequest, ProductCategoryResponse>
    val productDal: DAL<ProductRequest, ProductResponse>
}