package com.github.yokitoki.app

import com.github.yokitoki.app.request.LogoutRequest
import com.github.yokitoki.app.request.SignInRequest
import com.github.yokitoki.app.response.AuthCredentialsResponse
import com.github.yokitoki.app.response.Response
import com.github.yokitoki.app.response.RosterListResponse
import com.github.yokitoki.app.response.RosterResponse
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

/**
 * Zakupy
 */
class ZakupyRouting {
    /**
     * Auth
     */
    fun Routing.registerAuth() {
        post("/v1/auth/signin") {
            val body = call.receive<SignInRequest>()

            if (false) httpException(HttpStatusCode.BadRequest)
            if (false) httpException(HttpStatusCode.InternalServerError)

            call.respond(AuthCredentialsResponse())
        }

        post("/v1/auth/logout") {
            val body = call.receive<LogoutRequest>()

            if (false) httpException(HttpStatusCode.BadRequest)

            call.respond(
                Response(
                    status = 0,
                    message = "message",
                    timestamp = 0,
                    success = false
                )
            )
        }
    }

    /**
     * Roster
     */
    fun Routing.registerRoster() {
//        authenticate("bearerAuth") {
            get("/v1/roster") {
                if (false) httpException(HttpStatusCode.InternalServerError)

                call.respond(RosterListResponse())
            }
//        }

//        authenticate("bearerAuth") {
            get("/v1/roster/{uuid}") {
                val uuid = call.getPath<String>("uuid")

                if (false) httpException(HttpStatusCode.NotFound)
                if (false) httpException(HttpStatusCode.InternalServerError)

                call.respond(RosterResponse())
//            }
        }
    }
}
