package com.dicoding.frency

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.frency.data.FranchiseRepository
import com.dicoding.frency.data.UserRepository
import com.dicoding.frency.di.Injection
import com.dicoding.frency.ui.account.AccountViewModel
import com.dicoding.frency.ui.editprofile.EditProfileViewModel
import com.dicoding.frency.ui.home.HomeViewModel
//import com.dicoding.frency.ui.detail.DetailViewModel
import com.dicoding.frency.ui.login.LoginViewModel
import com.dicoding.frency.ui.register.RegisterViewModel

class ViewModelFactory(
    private val repository: UserRepository,
    private val repositoryFranchise: FranchiseRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AccountViewModel::class.java) -> {
                AccountViewModel(repository) as T
            }
            modelClass.isAssignableFrom(EditProfileViewModel::class.java) -> {
                EditProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repositoryFranchise) as T
            }
//            modelClass.isAssignableFrom(AccountViewModel::class.java) -> {
//                DetailViewModel(repository) as T
//            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideRepository(context) ,
                    Injection.provideRepositoryFranchise(context) ,
                )
            }.also { INSTANCE = it }
    }
}