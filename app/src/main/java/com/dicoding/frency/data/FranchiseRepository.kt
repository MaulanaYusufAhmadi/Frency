package com.dicoding.frency.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.frency.data.pref.UserPreference
import com.dicoding.frency.data.remote.request.FranchiseRequest
import com.dicoding.frency.data.remote.request.RegisterRequest
import com.dicoding.frency.data.remote.response.GetAllFranchiseResponse
import com.dicoding.frency.data.remote.response.RegisterResponse
import com.dicoding.frency.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.first

class FranchiseRepository constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {

    fun getAllFranchise(): LiveData<Result<List<GetAllFranchiseResponse>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllFranchises()
            emit(Result.Success(response))

        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }


    companion object {
        @Volatile
        private var instance: FranchiseRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): FranchiseRepository =
            instance ?: synchronized(this) {
                instance ?: FranchiseRepository(apiService, userPreference)
            }.also { instance = it }
    }

}