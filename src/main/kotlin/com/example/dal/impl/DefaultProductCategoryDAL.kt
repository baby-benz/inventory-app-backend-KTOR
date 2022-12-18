package com.example.dal.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.dal.ProductCategoryDAL
import com.example.domain.dto.request.ProductCategoryRequest
import com.example.domain.dto.response.ProductCategoryResponse
import com.example.domain.models.ProducersProductCategories
import com.example.domain.models.ProductCategories
import com.example.domain.models.ProductCategory
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.util.*

class DefaultProductCategoryDAL : ProductCategoryDAL {
    override suspend fun inListEntitiesSize(ids: Collection<UUID>): Long = dbQuery {
        ProductCategory.find { ProductCategories.id inList ids }.count()
    }

    override suspend fun all(): Iterable<ProductCategoryResponse> = dbQuery {
        ProductCategory.all().map { it.toResponse() }
    }

    override suspend fun find(id: UUID): ProductCategoryResponse? = dbQuery {
        ProductCategory.find { ProductCategories.id eq id }.singleOrNull()?.toResponse()
    }

    override suspend fun save(id: UUID, toSave: ProductCategoryRequest): ProductCategoryResponse = dbQuery {
        ProductCategory.new(id) { category = toSave.category }.toResponse()
    }

    override suspend fun save(toSave: ProductCategoryRequest): ProductCategoryResponse = dbQuery {
        ProductCategory.new { category = toSave.category }.toResponse()
    }

    override suspend fun update(id: UUID, toUpdate: ProductCategoryRequest): Boolean = dbQuery {
        ProductCategories.update({ ProductCategories.id eq id }) {
            it[category] = toUpdate.category
        } > 0
    }

    override suspend fun delete(id: UUID) = dbQuery {
        ProducersProductCategories.deleteWhere { productCategoryId eq id }
        ProductCategories.deleteWhere { ProductCategories.id eq id } > 0
    }

    override suspend fun existsById(id: UUID): Boolean = dbQuery {
        !ProductCategories.select { ProductCategories.id eq id }.empty()
    }

    override suspend fun existsByCategory(category: String): Boolean = dbQuery {
        !ProductCategories.select { ProductCategories.category eq category }.empty()
    }
}
