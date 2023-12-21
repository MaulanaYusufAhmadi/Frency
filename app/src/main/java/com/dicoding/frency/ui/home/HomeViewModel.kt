package com.dicoding.frency.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.frency.data.FranchiseRepository
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.remote.response.GetAllFranchiseResponse

class HomeViewModel(private val repository: FranchiseRepository): ViewModel() {

    fun getAllFranchise(): LiveData<Result<GetAllFranchiseResponse>> {
        return repository.getAllFranchise()
    }
}