package com.example.domain.model

import com.example.service.impl.so.impl.ProductCategorySO
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object ProductCategories : UUIDTable("category") {
    val category = varchar("category", 256)
}

class ProductCategory(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ProductCategory>(ProductCategories)

    var category by ProductCategories.category

    fun toSo() = ProductCategorySO(this)
}