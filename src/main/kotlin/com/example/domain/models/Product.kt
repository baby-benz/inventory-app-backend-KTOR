package com.example.domain.models

import com.example.domain.dto.response.ProductResponse
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Products : UUIDTable("product") {
    val name = varchar("name", 256)
    val price = double("price")
    val producer = reference("producer_id", Producers)
    val supplier = reference("supplier_id", Suppliers)
}

class Product(id: EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<Product>(Products)

    var name by Products.name
    var price by Products.price
    var producer by Producer referencedOn Products.producer
    var supplier by Supplier referencedOn Products.supplier

    fun toResponse() = ProductResponse(this)
}