package com.example.domain.model

import org.jetbrains.exposed.dao.id.UUIDTable

object ProducersProductCategories: UUIDTable("product_category") {
    val producerId = reference("producer_id", Producers)
    val productCategoryId = reference("product_category_id", ProductCategories)
}