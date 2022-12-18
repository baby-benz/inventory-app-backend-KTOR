package com.example.plugins

import com.example.routes.producerRouting
import com.example.routes.productCategoryRouting
import com.example.routes.productRouting
import com.example.routes.supplierRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        supplierRouting()
        productRouting()
        producerRouting()
        productCategoryRouting()
    }
}
