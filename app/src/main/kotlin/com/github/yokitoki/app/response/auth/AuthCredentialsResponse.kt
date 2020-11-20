package com.github.yokitoki.app.response.auth

data class AuthCredentialsResponse(
    val accessToken: String,
    val refreshToken: String
)
