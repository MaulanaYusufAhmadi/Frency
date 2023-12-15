package com.dicoding.frency.data.pref

data class UserModel(
    val username: String,
    val token: String ,
    val isLogin: Boolean = false
)