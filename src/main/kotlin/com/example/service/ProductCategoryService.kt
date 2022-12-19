package com.example.service

import com.example.domain.dto.request.product_category.ProductCategoryRequest
import com.example.domain.dto.response.product_category.ProductCategoryResponse

interface ProductCategoryService : Service<ProductCategoryRequest, ProductCategoryResponse>