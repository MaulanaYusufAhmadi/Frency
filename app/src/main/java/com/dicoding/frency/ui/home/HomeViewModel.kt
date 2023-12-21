package com.dicoding.frency.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.frency.data.FranchiseRepository
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.remote.response.GetAllFranchiseResponse

class HomeViewModel(private val repository: FranchiseRepository): ViewModel() {

    fun getAllFranchise(): LiveData<Result<GetAllFranchiseResponse>> {
//        Log.d("inpo3", "res succ dr viewnm")

        return repository.getAllFranchise()
    }
}