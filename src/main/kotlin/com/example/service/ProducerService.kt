package com.example.service

import com.example.dal.DAL
import com.example.domain.dto.request.ProducerRequest
import com.example.domain.dto.request.ProductCategoryRequest
import com.example.domain.dto.response.ProducerResponse
import com.example.domain.dto.response.ProductCategoryResponse

interface ProducerService : Service<ProducerRequest, ProducerResponse> {
    val productCategoryDal: DAL<ProductCategoryRequest, ProductCategoryResponse>
}