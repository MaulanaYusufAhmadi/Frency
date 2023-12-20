package com.dicoding.frency.data.remote.retrofit

import com.dicoding.frency.data.remote.request.LoginRequest
import com.dicoding.frency.data.remote.request.RegisterRequest
import com.dicoding.frency.data.remote.request.UpdateProfileRequest
import com.dicoding.frency.data.remote.response.LoginResponse
import com.dicoding.frency.data.remote.response.RegisterResponse
import com.dicoding.frency.data.remote.response.UpdateResponse
import retrofit2.http.*

interface ApiService {

    @POST("users")
    suspend fun createUser(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse


    @PATCH("users/current")
    suspend fun updateUser(
        @Header("Authorization") authorization: String,
        @Body updateRequest: UpdateProfileRequest
    ): UpdateResponse


}