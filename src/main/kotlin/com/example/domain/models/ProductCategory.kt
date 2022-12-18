package com.example.domain.models

import com.example.domain.dto.response.ProductCategoryResponse
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object ProductCategories : UUIDTable("category") {
    val category = varchar("category", 256)
}

class ProductCategory(id: EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<ProductCategory>(ProductCategories)

    var category by ProductCategories.category

    fun toResponse() = ProductCategoryResponse(this)
}