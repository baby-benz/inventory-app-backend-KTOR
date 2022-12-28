package com.example.service

import com.example.domain.dto.request.producer.ProducerRequest
import com.example.domain.dto.response.producer.CardProducerResponse
import com.example.domain.dto.response.producer.DefaultProducerResponse
import java.util.*

interface ProducerService : Service<ProducerRequest, DefaultProducerResponse> {
    suspend fun allCard(): Collection<CardProducerResponse>

    suspend fun getCard(id: UUID): CardProducerResponse
}