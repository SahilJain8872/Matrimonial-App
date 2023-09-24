package com.example.matrimonialapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matrimonialapp.repository.MainRepository
import com.example.matrimonialapp.core.Response
import com.example.matrimonialapp.db.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository) : ViewModel() {

    var usersList = MutableLiveData<Response<List<UserEntity>>>()

    fun getUsers(userCount: Int) {
        usersList.value = Response.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val result = repository.getUsers(userCount.toString())
                withContext(Dispatchers.Main){
                    result.observeForever { data->
                        if(data.isNullOrEmpty()){
                            usersList.postValue(Response.error(Throwable("unable to fetch users")))
                        }else{
                            usersList.postValue(Response.success(data))
                        }
                    }
                }
            }catch (e: Exception){
                usersList.postValue(Response.error(Throwable("unable to fetch users")))
            }
        }
    }

    fun updateUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserInDB(user)
        }
    }
}