package com.caldoconf.service

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

data class Conf(val title: String)

var confs = listOf(Conf("1st Caldo"), Conf("2nd Caldo"))

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        get("/") {
            call.respondText("Welcome to CaldoConf!")
        }

        route("/confs") {
            get {
                call.respond(mapOf("confs" to confs))
            }

            post {
                val proposedConf = call.receive<Conf>()
                confs += proposedConf
                call.respond(mapOf("confs" to confs))
            }
        }
    }
}
