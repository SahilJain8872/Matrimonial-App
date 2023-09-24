package com.example.matrimonialapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matrimonialapp.network.Model
import com.example.matrimonialapp.repository.MainRepository
import com.example.matrimonialapp.core.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    var usersList = MutableLiveData<Response<Model.Users>>()

    fun getUsers(userCount: Int) {
        usersList.value = Response.loading()
        viewModelScope.launch(Dispatchers.IO) {
            usersList.postValue(mainRepository.getUsers(userCount.toString()))
        }
    }
}