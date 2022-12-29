package com.example.service

import com.example.domain.dto.request.product.ProductRequest
import com.example.domain.dto.response.product.CardProductResponse
import com.example.domain.dto.response.product.DefaultProductResponse
import java.util.*

interface ProductService : Service<ProductRequest, DefaultProductResponse> {
    suspend fun allAsCard(): Collection<CardProductResponse>

    suspend fun getAsCard(id: UUID): CardProductResponse

    suspend fun saveAsCard(toSave: ProductRequest): CardProductResponse

    suspend fun updateAsCard(id: UUID, toUpdate: ProductRequest): CardProductResponse
}