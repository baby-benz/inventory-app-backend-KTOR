package com.example.routes

import com.example.domain.dto.request.product.ProductRequest
import com.example.service.ProductService
import com.example.service.impl.DefaultProductService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.productRouting() {
    val productService: ProductService = DefaultProductService()

    route("products") {
        get {
            when (call.request.queryParameters["forCard"]) {
                null, "0" -> {
                    call.respond(productService.all())
                }
                "1" -> {
                    call.respond(productService.allCard())
                }
                else -> {
                    call.respondText("forCard parameter could only be 0 or 1", status = HttpStatusCode.BadRequest)
                }
            }
        }
    }

    route("product") {
        post {
            val product = call.receive<ProductRequest>()
            val savedProduct = productService.save(product)
            call.respond(status = HttpStatusCode.Created, savedProduct)
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
                    call.respond(productService.get(id))
                }
                "1" -> {
                    call.respond(productService.getCard(id))
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
            val product = call.receive<ProductRequest>()
            val updatedProduct = productService.update(id, product)
            call.respond(updatedProduct)
        }
        delete("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@delete call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            productService.delete(id)
            call.respondText("Product with id $id removed successfully")
        }
    }
}