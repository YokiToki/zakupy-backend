package com.github.yokitoki.app

import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.netty.NettyApplicationEngine
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
object Main {
    private lateinit var engine: NettyApplicationEngine

    @KtorExperimentalAPI
    @JvmStatic
    fun main(args: Array<String>) {
        System.setProperty("user.timezone", "UTC")
        dotenv {
            systemProperties = true
        }

        val applicationEnvironment = commandLineEnvironment(args)
        engine = NettyApplicationEngine(applicationEnvironment)
        engine.start(wait = false)

        Runtime.getRuntime().addShutdownHook(Thread(Runnable {
            engine.stop(3000L, 5000L)
        }))
    }
}
