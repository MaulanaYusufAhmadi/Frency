package com.dicoding.frency.data.remote.retrofit

import com.dicoding.frency.data.pref.LoginRequest
import com.dicoding.frency.data.pref.RegisterRequest
import com.dicoding.frency.data.remote.response.LoginResponse
import com.dicoding.frency.data.remote.response.RegisterFranchisorResponse
import retrofit2.http.*

interface ApiService {

    @POST("user/register")
    suspend fun createUser(
        @Body registerRequest: RegisterRequest
    ): RegisterFranchisorResponse

    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse


}