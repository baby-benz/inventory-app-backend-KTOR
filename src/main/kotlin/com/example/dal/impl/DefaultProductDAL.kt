package com.example.dal.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.dal.ProductDAL
import com.example.domain.dto.request.product.ProductRequest
import com.example.domain.model.Producer
import com.example.domain.model.Product
import com.example.domain.model.Products
import com.example.domain.model.Supplier
import com.example.service.impl.so.impl.ProductSO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import java.util.*

class DefaultProductDAL : ProductDAL {
    override suspend fun all(): Iterable<ProductSO> = dbQuery {
        Product.all().map { it.toSo() }
    }

    override suspend fun find(id: UUID): ProductSO? = dbQuery {
        Product.find { Products.id eq id }.singleOrNull()?.toSo()
    }

    override suspend fun save(toSave: ProductRequest): ProductSO = dbQuery {
        Product.new {
            name = toSave.name
            price = toSave.price
            producer = Producer[toSave.producerId]
            supplier = Supplier[toSave.supplierId]
        }.toSo()
    }

    override suspend fun save(id: UUID, toSave: ProductRequest): ProductSO = dbQuery {
        Product.new(id) {
            name = toSave.name
            price = toSave.price
            producer = Producer[toSave.producerId]
            supplier = Supplier[toSave.supplierId]
        }.toSo()
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
        !Product.find { Products.id eq id }.empty()
    }

    override suspend fun existsByProducer(producerId: UUID): Boolean = dbQuery {
        !Product.find { Products.producer eq producerId }.empty()
    }

    override suspend fun existsBySupplier(supplierId: UUID): Boolean = dbQuery {
        !Product.find { Products.supplier eq supplierId }.empty()
    }
}