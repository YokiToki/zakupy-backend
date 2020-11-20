package com.github.yokitoki.app

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.AutoHeadResponse
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.HSTS
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.JacksonConverter
import io.ktor.jackson.jackson
import io.ktor.locations.Locations
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.core.component.KoinApiExtension

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KoinApiExtension
@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(Locations) {
    }

    install(AutoHeadResponse)

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        header(HttpHeaders.Authorization)
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(HSTS) {
        includeSubDomains = true
    }

//    install(Authentication) {
//    }

    install(ContentNegotiation) {
        register(ContentType.Application.Json, JacksonConverter())
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }
            exception<HttpException> { cause ->
                call.respond(cause.code, cause.description)
            }
        }

        get("/health") {
            call.respond("ok")
        }

        ZakupyRouting().apply {
            registerAuth()
            registerRoster()
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

