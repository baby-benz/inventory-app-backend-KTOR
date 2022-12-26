package com.example.domain.model

import com.example.service.impl.so.impl.ProducerSO
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Producers : UUIDTable("producer") {
    val name = varchar("name", 256)
}

class Producer(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Producer>(Producers)

    var name by Producers.name
    var productCategories by ProductCategory via ProducersProductCategories

    fun toSo() = ProducerSO(this)
}