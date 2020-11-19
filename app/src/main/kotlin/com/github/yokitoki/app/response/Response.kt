package com.github.yokitoki.app.response

data class Response(
    val status: Int,
    val message: String,
    val timestamp: Long,
    val success: Boolean,
    val data: Any? = null
)
