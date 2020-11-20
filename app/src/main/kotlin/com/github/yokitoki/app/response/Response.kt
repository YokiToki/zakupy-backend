package com.github.yokitoki.app.response

data class Response(
    val status: Int = 200,
    val message: String = "OK",
    val timestamp: Long = 10L,
    val success: Boolean = true,
    val data: Any? = null
)
