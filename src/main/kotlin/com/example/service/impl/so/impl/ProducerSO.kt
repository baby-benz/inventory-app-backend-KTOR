package com.example.service.impl.so.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.domain.dto.response.producer.CardProducerResponse
import com.example.domain.dto.response.producer.DefaultProducerResponse
import com.example.domain.model.Producer
import com.example.service.impl.so.ServiceObject

data class ProducerSO(override val entity: Producer) : ServiceObject<DefaultProducerResponse> {
    override suspend fun toDefaultResponse() = dbQuery {
        DefaultProducerResponse(
            entity.id.value,
            entity.name,
            entity.productCategories.map { it.id.value }
        )
    }

    suspend fun toCardResponse() = dbQuery {
        CardProducerResponse(
            entity.id.value,
            entity.name
        )
    }
}