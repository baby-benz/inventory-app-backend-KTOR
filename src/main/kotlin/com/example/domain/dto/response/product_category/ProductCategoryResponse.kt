package com.example.domain.dto.response.product_category

import com.example.domain.dto.response.Response

abstract class ProductCategoryResponse : Response() {
    abstract val category: String
}