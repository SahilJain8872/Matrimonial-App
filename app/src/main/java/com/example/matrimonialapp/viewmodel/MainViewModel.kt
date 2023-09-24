package com.example.matrimonialapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matrimonialapp.activity.MainActivity
import com.example.matrimonialapp.repository.MainRepository
import com.example.matrimonialapp.core.Response
import com.example.matrimonialapp.db.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    var usersList = MutableLiveData<Response<List<UserEntity>>>()

    fun getUsers(userCount: Int) {
        usersList.value = Response.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val result = mainRepository.getUsers(userCount.toString())
                withContext(Dispatchers.Main){
                    result?.observe(MainActivity.lifecycleOwner){ data->
                        if(data.isNullOrEmpty()){
                            usersList.postValue(Response.error(Throwable("unable to fetch users")))
                        }else{
                            usersList.postValue(Response.success(data))
                        }
                    } ?:  usersList.postValue(Response.error(Throwable("unable to fetch users")))
                }
            }catch (e: Exception){
                usersList.postValue(Response.error(Throwable("unable to fetch users")))
            }
        }
    }

    fun updateUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.updateUserInDB(user)
        }
    }
}