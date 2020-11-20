package com.github.yokitoki.app

import com.github.yokitoki.app.di.module
import com.github.yokitoki.app.mock.RosterMock
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
import org.junit.AfterClass
import org.junit.BeforeClass
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@KoinApiExtension
@KtorExperimentalAPI
class HttpAPITest {
    companion object {
        @BeforeClass
        @JvmStatic
        fun start() {
            startKoin {
                modules(module)
            }
        }

        @AfterClass
        @JvmStatic
        fun stop() {
            stopKoin()
        }
    }

    /**
     * @see ZakupyRouting.registerAuth
     */
    @Test
    fun testSignIn() {
        withTestApplication {
            handleRequest(HttpMethod.Post, "/v1/auth/signin") {
                addHeader(HttpHeaders.Accept, "application/json")
                addHeader(HttpHeaders.ContentType, "application/json")
                setBodyJson(SignInRequest("user@example.org", "string"))
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ZakupyRouting.registerAuth
     */
    @Test
    fun testLogout() {
        withTestApplication {
            handleRequest(HttpMethod.Post, "/v1/auth/logout") {
                addHeader(HttpHeaders.Accept, "application/json")
                addHeader(HttpHeaders.ContentType, "application/json")
                setBodyJson(LogoutRequest("string"))
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ZakupyRouting.registerRoster
     */
    @Test
    fun testGetV1Roster() {
        withTestApplication {
            handleRequest(HttpMethod.Get, "/v1/roster") {
                addHeader(HttpHeaders.Accept, "application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ZakupyRouting.registerRoster
     */
    @Test
    fun testGetV1RosterUuid() {
        withTestApplication {
            val uuid = RosterMock.getFirstUUid()
            handleRequest(HttpMethod.Get, "/v1/roster/$uuid") {
            }.apply {
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
