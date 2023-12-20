package com.dicoding.frency.data.remote.request

data class UpdateProfileRequest (
    val name: String,
    val email: String,
    val password: String,
    val role: String
)