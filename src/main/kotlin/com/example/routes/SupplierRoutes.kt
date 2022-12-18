package com.example.routes

import com.example.domain.dto.request.SupplierRequest
import com.example.service.SupplierService
import com.example.service.impl.DefaultSupplierService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.supplierRouting() {
    val service: SupplierService = DefaultSupplierService()

    route("suppliers") {
        get {
            call.respond(service.all())
        }
    }

    route("supplier") {
        post {
            val supplier = call.receive<SupplierRequest>()
            val savedSupplier = service.save(supplier)
            call.respond(status = HttpStatusCode.Created, savedSupplier)
        }
        get("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@get call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            val supplier = service.get(id)
            call.respond(supplier)
        }
        put("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@put call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            val supplier = call.receive<SupplierRequest>()
            val updatedSupplier = service.update(id, supplier)
            call.respond(updatedSupplier)
        }
        delete("{id?}") {
            val id = UUID.fromString(
                call.parameters["id"] ?: return@delete call.respondText(
                    "Id expected",
                    status = HttpStatusCode.BadRequest
                )
            )
            service.delete(id)
            call.respondText("Supplier with id $id removed successfully")
        }
    }
}