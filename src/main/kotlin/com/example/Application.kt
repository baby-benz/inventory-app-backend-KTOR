package com.example

import com.example.dal.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    val driverClassName = environment.config.property("db.driverClassName").getString()
    val jdbcURL = environment.config.property("db.jdbcURL").getString()
    DatabaseFactory.init(driverClassName, jdbcURL)
    configureRouting()
    configureSerialization()
    configureValidation()
    configureExceptionHandling()
}
