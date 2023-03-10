package com.example.routes

import com.example.domain.dto.request.product_category.ProductCategoryRequest
import com.example.service.ProductCategoryService
import com.example.service.impl.DefaultProductCategoryService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.productCategoryRouting() {
    val productCategoryService: ProductCategoryService = DefaultProductCategoryService()

    route("product-categories") {
        get {
            call.respond(productCategoryService.all())
        }
    }

    route("product-category") {
        post {
            val productCategory = call.receive<ProductCategoryRequest>()
            val savedProductCategory = productCategoryService.save(productCategory)
            call.respond(status = HttpStatusCode.Created, savedProductCategory)
        }
        get("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@get call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            val productCategory = productCategoryService.get(id)
            call.respond(productCategory)
        }
        put("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@put call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            val productCategory = call.receive<ProductCategoryRequest>()
            val updatedProductCategory = productCategoryService.update(id, productCategory)
            call.respond(updatedProductCategory)
        }
        delete("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@delete call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            productCategoryService.delete(id)
            call.respondText("ProductCategory with id $id removed successfully")
        }
    }
}