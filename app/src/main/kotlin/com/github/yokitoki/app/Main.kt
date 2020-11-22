package com.github.yokitoki.app

import com.github.yokitoki.app.di.module
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.netty.NettyApplicationEngine
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@ExperimentalCoroutinesApi
object Main {
    private lateinit var engine: NettyApplicationEngine

    @KtorExperimentalAPI
    @JvmStatic
    fun main(args: Array<String>) {
        System.setProperty("user.timezone", "UTC")
        dotenv {
            systemProperties = true
            ignoreIfMissing = true
        }

        startKoin {
            modules(module)
        }

        val applicationEnvironment = commandLineEnvironment(args)
        engine = NettyApplicationEngine(applicationEnvironment)
        engine.start(wait = false)

        Runtime.getRuntime().addShutdownHook(Thread(Runnable {
            stopKoin()
            engine.stop(3000L, 5000L)
        }))
    }
}
