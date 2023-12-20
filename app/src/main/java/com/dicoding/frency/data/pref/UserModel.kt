package com.dicoding.frency.data.pref

data class UserModel(
    val id: Int,
    val name: String,
    val email: String,
    val token: String ,
    val role: String,
    val isLogin: Boolean = false
)