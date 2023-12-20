package com.dicoding.frency.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.UserRepository
import com.dicoding.frency.data.pref.UserModel
import com.dicoding.frency.data.remote.request.UpdateProfileRequest
import com.dicoding.frency.data.remote.response.LoginResponse
import com.dicoding.frency.data.remote.response.RegisterResponse
import com.dicoding.frency.data.remote.response.UpdateResponse
import kotlinx.coroutines.launch

class EditProfileViewModel(private val repository: UserRepository): ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }


    private val _createUserResult = MutableLiveData<Result<UpdateResponse>>()
    val createUserResult: LiveData<Result<UpdateResponse>> = _createUserResult

    fun updateProfile(name: String, email: String, password: String, phone: String, gender: String, avatar: String) {
        viewModelScope.launch {
            repository.updateProfile(name, email, password, phone, gender, avatar).collect{ result ->
                _createUserResult.value = result
            }
        }
    }
}