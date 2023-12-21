package com.dicoding.frency.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.frency.data.pref.UserPreference
import com.dicoding.frency.data.remote.response.GetAllFranchiseResponse
import com.dicoding.frency.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.first

class FranchiseRepository constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {

    fun getAllFranchise(): LiveData<Result<GetAllFranchiseResponse>> = liveData {
        emit(Result.Loading)
        val token = userPreference.getSession().first().token
        try {
            val response = apiService.getAllFranchises()
            Log.d("ingpo", response.toString())

            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("ingpo", e.message.toString())
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