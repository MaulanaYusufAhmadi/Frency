package com.dicoding.frency.data.remote.request

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String,
    val role: String
)

data class LoginRequest(
    val email: String,
    val password: String
)