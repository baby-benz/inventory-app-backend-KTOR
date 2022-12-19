package com.example.plugins

import com.example.service.impl.exceptions.BadReferenceException
import com.example.service.impl.exceptions.DuplicateCategoryException
import com.example.service.impl.exceptions.NotFoundException
import com.example.service.impl.exceptions.ReferenceViolationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureExceptionHandling() {
    install(StatusPages) {
        exception<RequestValidationException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
        }
        exception<BadReferenceException> { call, cause ->
            respondNotFound(call, cause)
        }
        exception<NotFoundException> { call, cause ->
            respondNotFound(call, cause)
        }
        exception<DuplicateCategoryException> { call, cause ->
            respondBadRequest(call, cause)
        }
        exception<ReferenceViolationException> { call, cause ->
            respondConflict(call, cause)
        }
        exception<Exception> { call, cause ->
            respondInternalServerError(call, cause)
        }
    }
}

suspend fun respondBadRequest(call: ApplicationCall, cause: Exception) {
    call.respond(HttpStatusCode.BadRequest, cause.message!!)
}

suspend fun respondNotFound(call: ApplicationCall, cause: Exception) {
    call.respond(HttpStatusCode.NotFound, cause.message!!)
}

suspend fun respondInternalServerError(call: ApplicationCall, cause: Exception) {
    call.respond(HttpStatusCode.InternalServerError, cause.message!!)
}

suspend fun respondConflict(call: ApplicationCall, cause: Exception) {
    call.respond(HttpStatusCode.Conflict, cause.message!!)
}