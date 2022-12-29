package com.example.dal.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.dal.ProducerDAL
import com.example.domain.dto.request.producer.ProducerRequest
import com.example.domain.model.*
import com.example.service.impl.so.impl.ProducerSO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import java.util.*

class DefaultProducerDAL : ProducerDAL {
    override suspend fun all(): Iterable<ProducerSO> = dbQuery {
        Producer.all().map { it.toSo() }
    }

    override suspend fun find(id: UUID): ProducerSO? = dbQuery {
        Producer.find { Producers.id eq id }.singleOrNull()?.toSo()
    }

    override suspend fun save(toSave: ProducerRequest): ProducerSO = dbQuery {
        val categories = ProductCategory.find { ProductCategories.id inList toSave.productCategoryIds }
        Producer.new {
            name = toSave.name
            productCategories = categories
        }.toSo()
    }

    override suspend fun save(
        id: UUID,
        toSave: ProducerRequest,
    ): ProducerSO = dbQuery {
        val categories = ProductCategory.find { ProductCategories.id inList toSave.productCategoryIds }
        Producer.new(id) {
            name = toSave.name
            productCategories = categories
        }.toSo()
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
            producer.productCategories =
                ProductCategory.find { ProductCategories.id inList toUpdate.productCategoryIds }
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

    override suspend fun updateAndGet(id: UUID, toUpdate: ProducerRequest): ProducerSO? = dbQuery {
        val recordsUpdated = Producers.update({ Producers.id eq id }) {
            it[name] = toUpdate.name
        }
        if (recordsUpdated < 1) {
            null
        } else {
            val producer = Producer[id]
            producer.name = toUpdate.name
            producer.productCategories =
                ProductCategory.find { ProductCategories.id inList toUpdate.productCategoryIds }
            producer.toSo()
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