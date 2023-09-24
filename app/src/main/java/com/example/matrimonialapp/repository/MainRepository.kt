package com.example.matrimonialapp.repository

import com.example.matrimonialapp.core.Mapper
import com.example.matrimonialapp.db.DBManager
import com.example.matrimonialapp.core.Response
import com.example.matrimonialapp.db.entity.UserEntity
import com.example.matrimonialapp.network.APIClient
import java.lang.Exception

class MainRepository {

    fun getUsers(queryData: String) = try{
        val result = APIClient.getApiInterface().getUsers(queryData).execute()
        if(result.isSuccessful){
                if (result.body() != null) {
                    insertUserListInDB(Mapper.getUserEntityFromUsers(result.body()!!))
                    Response.success(result.body())
                } else {
                    Response.error(Throwable("unable to fetch users"))
                }
        }else{
           Response.error(Throwable("unable to fetch users"))
        }
    }catch (e: Exception){
        Response.error(Throwable("unable to fetch users"))
    }

    fun insertUserInDB(userEntity: UserEntity) = DBManager.insertUser(userEntity)

    fun insertUserListInDB(users: List<UserEntity>) = DBManager.insertUserList(users)

    fun updateUserInDB(user: UserEntity) = DBManager.updateUser(user)

    fun getUsersListFromDB() = try{
        val result = DBManager.getUsersList()
        if(result.isNullOrEmpty()){
            Response.error(Throwable("unable to fetch users"))
        }else{
            Response.success(Mapper.getUsersFromUserEntity(result))
        }
    }catch (e: Exception){
        Response.error(Throwable("unable to fetch users"))
    }

}