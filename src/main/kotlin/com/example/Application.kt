package com.example

import com.example.dal.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.network.tls.certificates.*
import java.io.File

fun main(args: Array<String>) {
    generateSslCert()
    EngineMain.main(args)
}

fun generateSslCert() {
    val pass = "foobar"
    val keyStoreFile = File("build/keystore.jks")
    val keyStore = buildKeyStore {
        certificate("myCert") {
            password = pass
            domains = listOf("127.0.0.1", "0.0.0.0", "localhost")
        }
    }
    keyStore.saveToFile(keyStoreFile, pass)
}

fun Application.module() {
    val driverClassName = environment.config.property("db.driverClassName").getString()
    val jdbcURL = environment.config.property("db.jdbcURL").getString()
    DatabaseFactory.init(driverClassName, jdbcURL)
    configureRouting()
    configureSerialization()
    configureValidation()
    configureExceptionHandling()
}
