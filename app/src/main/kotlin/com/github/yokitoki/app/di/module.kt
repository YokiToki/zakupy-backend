package com.github.yokitoki.app.di

import com.github.yokitoki.app.controller.AuthController
import com.github.yokitoki.app.controller.RosterController
import org.koin.dsl.module

val module = module {
    single { AuthController() }
    single { RosterController() }
}
