package com.github.yokitoki.app

import com.github.yokitoki.app.request.LogoutRequest
import com.github.yokitoki.app.request.SignInRequest
import io.ktor.config.MapApplicationConfig
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.TestApplicationRequest
import io.ktor.server.testing.createTestEnvironment
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withApplication
import io.ktor.util.KtorExperimentalAPI
import kotlin.test.Test
import kotlin.test.assertEquals

@KtorExperimentalAPI
class HttpAPITest {
    /**
     * @see ZakupyRouting.signIn
     */
    @Test
    fun testSignIn() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/v1/auth/signin") {
                // @TODO: Your body here
                addHeader(HttpHeaders.Accept, "application/json")
                addHeader(HttpHeaders.ContentType, "application/json")
                setBodyJson(SignInRequest("user@example.org", "string"))
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ZakupyRouting.logout
     */
    @Test
    fun testLogout() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/v1/auth/logout") {
                // @TODO: Your body here
                addHeader(HttpHeaders.Accept, "application/json")
                addHeader(HttpHeaders.ContentType, "application/json")
                setBodyJson(LogoutRequest("string"))
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ZakupyRouting.getV1Roster
     */
    @Test
    fun testGetV1Roster() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/v1/roster") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ZakupyRouting.getV1RosterUuid
     */
    @Test
    fun testGetV1RosterUuid() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/v1/roster/{uuid}") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    private fun <R> withTestApplication(test: TestApplicationEngine.() -> R): R {
        return withApplication(createTestEnvironment()) {
            (environment.config as MapApplicationConfig).apply {
                put("jwt.secret", "TODO-change-this-supersecret-or-use-SECRET-env")
            }
            application.module()
            test()
        }
    }

    private fun TestApplicationRequest.setBodyJson(value: Any?) = setBody(Json.stringify(value))
}
