package com.dicoding.frency.data.pref

data class UserModel(
    val id: Int,
    val name: String,
    val email: String,
    val token: String,
    val role: String,
    val phone: String,
    val gender: String,
    val avatar: String,
    val isLogin: Boolean = false
)