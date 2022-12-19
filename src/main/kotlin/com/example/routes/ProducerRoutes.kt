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
    val service: ProducerService = DefaultProducerService()

    route("producers") {
        get {
            call.respond(service.all())
        }
    }

    route("producer") {
        post {
            val product = call.receive<ProducerRequest>()
            val savedProducer = service.save(product)
            call.respond(status = HttpStatusCode.Created, savedProducer)
        }
        get("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@get call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            call.respond(service.get(id))
        }
        put("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@put call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            val producer = call.receive<ProducerRequest>()
            val updatedProducer = service.update(id, producer)
            call.respond(updatedProducer)
        }
        delete("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@delete call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            service.delete(id)
            call.respondText("Producer with id $id removed successfully")
        }
    }
}