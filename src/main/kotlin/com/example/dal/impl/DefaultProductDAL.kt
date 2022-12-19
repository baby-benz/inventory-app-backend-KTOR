package com.example.dal.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.dal.ProductDAL
import com.example.domain.dto.request.ProductRequest
import com.example.domain.dto.response.ProductResponse
import com.example.domain.models.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.util.*

class DefaultProductDAL : ProductDAL {
    override suspend fun all(): List<ProductResponse> = dbQuery {
        Product.all().map { product -> product.toResponse() }
    }

    override suspend fun find(id: UUID): ProductResponse? = dbQuery {
        Product.find { Products.id eq id }.singleOrNull()?.toResponse()
    }

    override suspend fun save(toSave: ProductRequest): ProductResponse = dbQuery {
        Product.new {
            name = toSave.name
            price = toSave.price
            producer = when (toSave.producerId) {
                null -> { null }
                else -> { Producer[toSave.producerId] }
            }
            supplier = when (toSave.supplierId) {
                null -> { null }
                else -> { Supplier[toSave.supplierId] }
            }
        }.toResponse()
    }

    override suspend fun save(id: UUID, toSave: ProductRequest): ProductResponse = dbQuery {
        Product.new(id) {
            name = toSave.name
            price = toSave.price
            producer = when (toSave.producerId) {
                null -> { null }
                else -> { Producer[toSave.producerId] }
            }
            supplier = when (toSave.supplierId) {
                null -> { null }
                else -> { Supplier[toSave.supplierId] }
            }
        }.toResponse()
    }

    override suspend fun update(id: UUID, toUpdate: ProductRequest): Boolean = dbQuery {
        Products.update({ Products.id eq id }) {
            it[name] = toUpdate.name
            it[price] = toUpdate.price
            it[producer] = toUpdate.producerId
            it[supplier] = toUpdate.supplierId
        } > 0
    }

    override suspend fun delete(id: UUID): Boolean = dbQuery {
        Products.deleteWhere { Products.id eq id } > 0
    }

    override suspend fun existsById(id: UUID): Boolean = dbQuery {
        !Products.select { Products.id eq id }.empty()
    }
}