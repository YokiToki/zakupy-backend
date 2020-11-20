package com.github.yokitoki.app.controller

import com.github.yokitoki.app.httpException
import com.github.yokitoki.app.request.LogoutRequest
import com.github.yokitoki.app.request.SignInRequest
import com.github.yokitoki.app.response.auth.AuthCredentialsResponse
import com.github.yokitoki.app.response.Response
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive

class AuthController {
    suspend fun signIn(call: ApplicationCall): Response {
        val body = call.receive<SignInRequest>()

        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.InternalServerError)

        return Response(data = AuthCredentialsResponse("access-token", "refresh-token"))
    }

    suspend fun logout(call: ApplicationCall): Response {
        val body = call.receive<LogoutRequest>()

        if (false) httpException(HttpStatusCode.BadRequest)

        return Response()
    }
}
