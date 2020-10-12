package com.caldoconf.service

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(DefaultHeaders)
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
    }

    routing {
        get("/") {
            call.respondText("Welcome to CaldoConf!")
        }

        route("/conferences") {
            get {
                call.respond(
                        listOf(
                                Conference(
                                        "1",
                                        Date(),
                                        Location(40.4091552, -3.7161942, "Calle San Bernabé"),
                                        "The bloody CaldoConf is back biatches!"
                                ),
                                Conference(
                                        "2",
                                        Date(),
                                        Location(40.4091552, -3.7161942, "Calle San Bernabé"),
                                        "The bloody CaldoConf is back biatches!"
                                )
                        )
                )
            }

            get("/{id}") {
                call.respond(Conference(
                        call.parameters["id"]!!,
                        Date(),
                        Location(40.4091552, -3.7161942, "Calle San Bernabé"),
                        "The bloody CaldoConf is back biatches!"
                ))
            }
        }
    }
}
