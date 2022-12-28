package com.example.routes

import com.example.domain.dto.request.supplier.SupplierRequest
import com.example.service.SupplierService
import com.example.service.impl.DefaultSupplierService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.supplierRouting() {
    val supplierService: SupplierService = DefaultSupplierService()

    route("suppliers") {
        get {
            when (call.request.queryParameters["forCard"]) {
                null, "0" -> {
                    call.respond(supplierService.all())
                }
                "1" -> {
                    call.respond(supplierService.allCard())
                }
                else -> {
                    call.respondText("forCard parameter could only be 0 or 1", status = HttpStatusCode.BadRequest)
                }
            }
        }
    }

    route("supplier") {
        post {
            val supplier = call.receive<SupplierRequest>()
            val savedSupplier = supplierService.save(supplier)
            call.respond(status = HttpStatusCode.Created, savedSupplier)
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
                    call.respond(supplierService.get(id))
                }
                "1" -> {
                    call.respond(supplierService.getCard(id))
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
            val supplier = call.receive<SupplierRequest>()
            val updatedSupplier = supplierService.update(id, supplier)
            call.respond(updatedSupplier)
        }
        delete("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@delete call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            supplierService.delete(id)
            call.respondText("Supplier with id $id removed successfully")
        }
    }
}