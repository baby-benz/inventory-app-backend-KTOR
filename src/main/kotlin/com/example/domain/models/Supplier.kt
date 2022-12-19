package com.example.domain.models

import com.example.domain.dto.response.supplier.SupplierResponse
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Suppliers : UUIDTable("supplier") {
    val name = varchar("name", 256)
    val phone = varchar("phone_number", 15)
    val email = varchar("email", 64)
}

class Supplier(id: EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<Supplier>(Suppliers)

    var name by Suppliers.name
    var phone by Suppliers.phone
    var email by Suppliers.email

    fun toResponse() = SupplierResponse(this)
}