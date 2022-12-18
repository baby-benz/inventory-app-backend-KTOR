package com.example.routes

import com.example.domain.dto.request.ProductRequest
import com.example.service.ProductService
import com.example.service.impl.DefaultProductService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.productRouting() {
    val service: ProductService = DefaultProductService()

    route("products") {
        get {
            call.respond(service.all())
        }
    }

    route("product") {
        post {
            val product = call.receive<ProductRequest>()
            val savedProduct = service.save(product)
            call.respond(status = HttpStatusCode.Created, savedProduct)
        }
        get("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@get call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            val product = service.get(id)
            call.respond(product)
        }
        put("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@put call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            val product = call.receive<ProductRequest>()
            val updatedProduct = service.update(id, product)
            call.respond(updatedProduct)
        }
        delete("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@delete call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            service.delete(id)
            call.respondText("Product with id $id removed successfully")
        }
    }
}