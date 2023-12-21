package com.dicoding.frency.data

import com.dicoding.frency.data.remote.request.LoginRequest
import com.dicoding.frency.data.remote.request.RegisterRequest
import com.dicoding.frency.data.remote.retrofit.ApiService
import com.dicoding.frency.data.pref.UserModel
import com.dicoding.frency.data.pref.UserPreference
import com.dicoding.frency.data.remote.request.UpdateProfileRequest
import com.dicoding.frency.data.remote.response.LoginResponse
import com.dicoding.frency.data.remote.response.RegisterResponse
import com.dicoding.frency.data.remote.response.UpdateResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }


    suspend fun createUser(name: String, email: String, password: String): Flow<Result<RegisterResponse>> = flow {
        emit(Result.Loading)
        try {
            val registerRequest = RegisterRequest(name, email, password, "franchisee")
            val response = apiService.createUser(registerRequest)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }

    suspend fun login(email: String, password: String): Flow<Result<LoginResponse>> = flow {
        emit(Result.Loading)
        try {
            // Panggil metode createUser pada apiService
            val request = LoginRequest(email, password)
            val response = apiService.login(request)
//            userPreference.saveSession(UserModel(response.data.name, response.data.token))
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }

    suspend fun updateProfile(name: String, email: String, password: String, phone: String, gender: String, avatar: String): Flow<Result<UpdateResponse>> = flow {
        emit(Result.Loading)
        val token = userPreference.getSession().first().token
        try {
            val updateProfileRequest = UpdateProfileRequest(name, email, password, phone, gender, avatar, "franchisee")
            val response = apiService.updateUser("Bearer $token", updateProfileRequest)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}