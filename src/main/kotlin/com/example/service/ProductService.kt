package com.example.service

import com.example.domain.dto.request.product.ProductRequest
import com.example.domain.dto.response.product.CardProductResponse
import com.example.domain.dto.response.product.DefaultProductResponse
import java.util.*

interface ProductService : Service<ProductRequest, DefaultProductResponse> {
    suspend fun allCard(): Collection<CardProductResponse>

    suspend fun getCard(id: UUID): CardProductResponse
}