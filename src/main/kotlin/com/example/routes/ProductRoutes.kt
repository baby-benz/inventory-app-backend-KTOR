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
                null, "0" -> call.respond(productService.all())
                "1" -> call.respond(productService.allAsCard())
                else -> badCardParameterResponse(call)
            }
        }
    }

    route("product") {
        post {
            val product = call.receive<ProductRequest>()
            when (call.request.queryParameters["forCard"]) {
                null, "0" -> call.respond(status = HttpStatusCode.Created, productService.save(product))
                "1" -> call.respond(status = HttpStatusCode.Created, productService.saveAsCard(product))
                else -> badCardParameterResponse(call)
            }
        }
        get("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@get call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            when (call.request.queryParameters["forCard"]) {
                null, "0" -> call.respond(productService.get(id))
                "1" -> call.respond(productService.getAsCard(id))
                else -> badCardParameterResponse(call)
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
            when (call.request.queryParameters["forCard"]) {
                null, "0" -> call.respond(productService.update(id, product))
                "1" -> call.respond(productService.updateAsCard(id, product))
                else -> badCardParameterResponse(call)
            }
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

private suspend fun badCardParameterResponse(call: ApplicationCall) {
    call.respondText("forCard parameter could only be 0 or 1", status = HttpStatusCode.BadRequest)
}