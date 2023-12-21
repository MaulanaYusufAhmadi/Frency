package com.dicoding.frency.di

import android.content.Context
import android.util.Log
import com.dicoding.frency.data.FranchiseRepository
import com.dicoding.frency.data.UserRepository
import com.dicoding.frency.data.pref.UserPreference
import com.dicoding.frency.data.pref.dataStore
import com.dicoding.frency.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        Log.d("inpo4", user.token)

        val userPreference = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(apiService, userPreference)
    }
    fun provideRepositoryFranchise(context: Context): FranchiseRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        val userPreference = UserPreference.getInstance(context.dataStore)
        return FranchiseRepository.getInstance(apiService, userPreference)
    }

}