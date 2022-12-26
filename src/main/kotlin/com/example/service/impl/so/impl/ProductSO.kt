package com.example.service.impl.so.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.domain.dto.response.product.CardProductResponse
import com.example.domain.dto.response.product.DefaultProductResponse
import com.example.domain.model.Product
import com.example.service.impl.so.ServiceObject

data class ProductSO(override val entity: Product) : ServiceObject<DefaultProductResponse> {
    override suspend fun toDefaultResponse() = dbQuery {
        DefaultProductResponse(
            entity.id.value,
            entity.name,
            entity.price,
            entity.producer.id.value,
            entity.supplier.id.value
        )
    }

    suspend fun toCardResponse() = dbQuery {
        CardProductResponse(
            entity.id.value,
            entity.name,
            entity.price,
            entity.producer.name,
            entity.supplier.name
        )
    }
}