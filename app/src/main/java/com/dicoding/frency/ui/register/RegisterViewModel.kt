package com.dicoding.frency.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.repository.UserRepository
import com.dicoding.frency.data.remote.response.RegisterFranchiseeResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {
    private val _createUserResult = MutableLiveData<Result<RegisterFranchiseeResponse>>()
    val createUserResult: LiveData<Result<RegisterFranchiseeResponse>> = _createUserResult

    fun createUser(email: String, name: String, username: String , password: String) {
        viewModelScope.launch {
            repository.createUser(email, name, username , password).collect{ result ->
                _createUserResult.value = result
            }
        }
    }
}