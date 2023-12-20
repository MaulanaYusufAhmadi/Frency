package com.dicoding.frency.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.UserRepository
import com.dicoding.frency.data.pref.UserModel
import com.dicoding.frency.data.remote.response.LoginResponse
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: UserRepository): ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}