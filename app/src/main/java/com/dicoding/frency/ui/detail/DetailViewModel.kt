//package com.dicoding.frency.ui.detail
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MediatorLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.dicoding.frency.data.UserRepository
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class DetailViewModel(private val repository: UserRepository) : ViewModel() {
//
//    private val _githubUserDetail = MediatorLiveData<Result<DetailUserResponse>>()
//    val githubUserDetail: LiveData<Result<DetailUserResponse>> = _githubUserDetail
//
//    private val _isFavorite = MediatorLiveData<Boolean>()
//    val isFavorite: LiveData<Boolean> = _isFavorite
//
//    fun getGithubUserDetail(username: String) {
//        val liveData = repository.getDetailUser(username)
//        _githubUserDetail.addSource(liveData) { result ->
//            _githubUserDetail.value = result
//        }
//    }
//
//    fun isFavorite(username: String) {
//        val liveData = repository.isFavorite(username)
//        _isFavorite.addSource(liveData) { result ->
//            _isFavorite.value = result
//        }
//    }
//
//    fun saveFavorite(favorite: FavoriteUser) =
//        viewModelScope.launch(Dispatchers.IO) { repository.saveFavorite(favorite) }
//
//    fun deleteFavorite(favorite: FavoriteUser) =
//        viewModelScope.launch(Dispatchers.IO) { repository.delete(favorite) }
//}