package com.example.dal.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.dal.ProducerDAL
import com.example.domain.dto.request.ProducerRequest
import com.example.domain.dto.response.ProducerResponse
import com.example.domain.models.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*

class DefaultProducerDAL : ProducerDAL {
    override suspend fun all(): List<ProducerResponse> = dbQuery {
        Producer.all().map { producer -> producer.toResponse() }
    }

    override suspend fun find(id: UUID): ProducerResponse? = dbQuery {
        Producer.find { Producers.id eq id }.singleOrNull()?.toResponse()
    }

    override suspend fun save(toSave: ProducerRequest): ProducerResponse = dbQuery {
        val categories = ProductCategory.find { ProductCategories.id inList toSave.productCategoryIds }
        Producer.new {
            name = toSave.name
            productCategories = categories
        }.toResponse()
    }

    override suspend fun save(id: UUID, toSave: ProducerRequest): ProducerResponse = dbQuery {
        val categories = ProductCategory.find { ProductCategories.id inList toSave.productCategoryIds }
        Producer.new(id) {
            name = toSave.name
            productCategories = categories
        }.toResponse()
    }

    override suspend fun update(id: UUID, toUpdate: ProducerRequest): Boolean = dbQuery {
        val recordsUpdated = Producers.update({ Producers.id eq id }) {
            it[name] = toUpdate.name
        }
        if (recordsUpdated < 1) {
            false
        } else {
            val producer = Producer[id]
            producer.name = toUpdate.name
            producer.productCategories = ProductCategory.find { ProductCategories.id inList toUpdate.productCategoryIds }
            /*ProducersProductCategories.deleteWhere { (producerId eq id) and (productCategoryId notInList toUpdate.productCategoryIds) }
            toUpdate.productCategoryIds.forEach { category ->
                ProducersProductCategories.insertIgnore {
                    it[producerId] = id
                    it[productCategoryId] = category
                }
            }*/
            true
        }
    }

    override suspend fun delete(id: UUID): Boolean = dbQuery {
        ProducersProductCategories.deleteWhere { producerId eq id }
        Producers.deleteWhere { Producers.id eq id } > 0
    }

    override suspend fun existsById(id: UUID): Boolean = dbQuery {
        !Producer.find { Producers.id eq id }.empty()
    }
}