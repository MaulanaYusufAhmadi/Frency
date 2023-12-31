package com.dicoding.frency.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.UserRepository
import com.dicoding.frency.data.remote.response.RegisterResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {
    private val _createUserResult = MutableLiveData<Result<RegisterResponse>>()
    val createUserResult: LiveData<Result<RegisterResponse>> = _createUserResult

    fun createUser(email: String, name: String , password: String) {
        viewModelScope.launch {
            repository.createUser(email, name , password).collect{ result ->
                _createUserResult.value = result
            }
        }
    }
}