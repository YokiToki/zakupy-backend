package com.github.yokitoki.app

import com.github.yokitoki.app.controller.AuthController
import com.github.yokitoki.app.controller.RosterController
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * Zakupy
 */
@KoinApiExtension
class ZakupyRouting : KoinComponent {
    /**
     * Auth
     */
    fun Routing.registerAuth() {
        post("/v1/auth/signin") {
            call.respond(get<AuthController>().signIn(call))
        }

        post("/v1/auth/logout") {
            call.respond(get<AuthController>().logout(call))
        }
    }

    /**
     * Roster
     */
    fun Routing.registerRoster() {
        get("/v1/roster") {
            call.respond(get<RosterController>().getAll())
        }

        get("/v1/roster/{uuid}") {
            call.respond(get<RosterController>().get(call))
        }
    }
}
