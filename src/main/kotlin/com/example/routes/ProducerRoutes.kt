package com.example.routes

import com.example.domain.dto.request.producer.ProducerRequest
import com.example.service.ProducerService
import com.example.service.impl.DefaultProducerService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.producerRouting() {
    val producerService: ProducerService = DefaultProducerService()

    route("producers") {
        get {
            when (call.request.queryParameters["forCard"]) {
                null, "0" -> {
                    call.respond(producerService.all())
                }
                "1" -> {
                    call.respond(producerService.allCard())
                }
                else -> {
                    call.respondText("forCard parameter could only be 0 or 1", status = HttpStatusCode.BadRequest)
                }
            }
        }
    }

    route("producer") {
        post {
            val product = call.receive<ProducerRequest>()
            val savedProducer = producerService.save(product)
            call.respond(status = HttpStatusCode.Created, savedProducer)
        }
        get("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@get call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            when (call.request.queryParameters["forCard"]) {
                null, "0" -> {
                    call.respond(producerService.get(id))
                }
                "1" -> {
                    call.respond(producerService.getCard(id))
                }
                else -> {
                    call.respondText("forCard parameter could only be 0 or 1", status = HttpStatusCode.BadRequest)
                }
            }
        }
        put("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@put call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            val producer = call.receive<ProducerRequest>()
            val updatedProducer = producerService.update(id, producer)
            call.respond(updatedProducer)
        }
        delete("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@delete call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            producerService.delete(id)
            call.respondText("Producer with id $id removed successfully")
        }
    }
}