package com.example.dal.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.dal.ProductCategoryDAL
import com.example.domain.dto.request.product_category.ProductCategoryRequest
import com.example.domain.model.ProducersProductCategories
import com.example.domain.model.ProductCategories
import com.example.domain.model.ProductCategory
import com.example.service.impl.so.impl.ProductCategorySO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import java.util.*

class DefaultProductCategoryDAL : ProductCategoryDAL {
    override suspend fun inListEntitiesSize(ids: Collection<UUID>): Long = dbQuery {
        ProductCategory.find { ProductCategories.id inList ids }.count()
    }

    override suspend fun all(): Iterable<ProductCategorySO> = dbQuery {
        ProductCategory.all().map { it.toSo() }
    }

    override suspend fun find(id: UUID): ProductCategorySO? = dbQuery {
        ProductCategory.find { ProductCategories.id eq id }.singleOrNull()?.toSo()
    }

    override suspend fun save(id: UUID, toSave: ProductCategoryRequest): ProductCategorySO = dbQuery {
        ProductCategory.new(id) { category = toSave.category }.toSo()
    }

    override suspend fun save(toSave: ProductCategoryRequest): ProductCategorySO = dbQuery {
        ProductCategory.new { category = toSave.category }.toSo()
    }

    override suspend fun update(id: UUID, toUpdate: ProductCategoryRequest): Boolean = dbQuery {
        ProductCategories.update({ ProductCategories.id eq id }) {
            it[category] = toUpdate.category
        } > 0
    }

    override suspend fun updateAndGet(id: UUID, toUpdate: ProductCategoryRequest): ProductCategorySO? = dbQuery {
        val recordsUpdated = ProductCategories.update({ ProductCategories.id eq id }) {
            it[category] = toUpdate.category
        }
        if (recordsUpdated < 1) {
            null
        } else {
            find(id)
        }
    }

    override suspend fun delete(id: UUID) = dbQuery {
        ProducersProductCategories.deleteWhere { productCategoryId eq id }
        ProductCategories.deleteWhere { ProductCategories.id eq id } > 0
    }

    override suspend fun existsById(id: UUID): Boolean = dbQuery {
        !ProductCategory.find { ProductCategories.id eq id }.empty()
    }

    override suspend fun existsByCategory(category: String): Boolean = dbQuery {
        !ProductCategory.find { ProductCategories.category eq category }.empty()
    }
}
