package com.example.domain.dto.response.product

import com.example.domain.dto.response.Response

abstract class ProductResponse : Response() {
    abstract val name: String
    abstract val price: Double
}