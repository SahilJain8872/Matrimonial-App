package com.example.matrimonialapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matrimonialapp.network.Model
import com.example.matrimonialapp.repository.MainRepository
import com.example.matrimonialapp.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    var usersList = MutableLiveData<Response<Model.Users>>()

    fun getUsers(userCount: Int) {
        usersList.value = Response.loading()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = mainRepository.getUsers(userCount.toString()).execute()
                if(result.isSuccessful){
                    usersList.postValue(
                        if (result.body() != null) {
                            Response.success(result.body())
                        } else {
                            Response.error(Throwable("unable to fetch users"))
                        }
                    )
                }else{
                    usersList.postValue(Response.error(Throwable("unable to fetch users")))
                }
            }
        }
    }
}