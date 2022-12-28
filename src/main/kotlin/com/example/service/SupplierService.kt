package com.example.service

import com.example.domain.dto.request.supplier.SupplierRequest
import com.example.domain.dto.response.producer.CardProducerResponse
import com.example.domain.dto.response.supplier.CardSupplierResponse
import com.example.domain.dto.response.supplier.SupplierResponse
import java.util.*

interface SupplierService : Service<SupplierRequest, SupplierResponse> {
    suspend fun allCard(): Collection<CardSupplierResponse>

    suspend fun getCard(id: UUID): CardSupplierResponse
}