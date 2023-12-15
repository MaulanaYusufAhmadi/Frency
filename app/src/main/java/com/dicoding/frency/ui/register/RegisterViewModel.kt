package com.dicoding.frency.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.UserRepository
import com.dicoding.frency.data.remote.response.RegisterFranchisorResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {
    private val _createUserResult = MutableLiveData<Result<RegisterFranchisorResponse>>()
    val createUserResult: LiveData<Result<RegisterFranchisorResponse>> = _createUserResult

    fun createUser(email: String, name: String, username: String , password: String) {
        viewModelScope.launch {
            repository.createUser(email, name, username , password).collect{ result ->
                _createUserResult.value = result
            }
        }
    }
}