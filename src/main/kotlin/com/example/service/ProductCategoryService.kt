package com.example.service

import com.example.domain.dto.request.ProductCategoryRequest
import com.example.domain.dto.response.ProductCategoryResponse

interface ProductCategoryService : Service<ProductCategoryRequest, ProductCategoryResponse>