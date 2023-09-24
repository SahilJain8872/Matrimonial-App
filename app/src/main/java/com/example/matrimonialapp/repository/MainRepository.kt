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
                    val userEntityList = Mapper.getUserEntityFromUsers(result.body()!!)
                    insertUserListInDB(userEntityList)
                    getUsersListFromDB()
                } else {
                    getUsersListFromDB()
                }
        }else{
            getUsersListFromDB()
        }
    }catch (e: Exception){
        getUsersListFromDB()
    }

    fun insertUserInDB(userEntity: UserEntity) = DBManager.insertUser(userEntity)

    fun insertUserListInDB(users: List<UserEntity>) = DBManager.insertUserList(users)

    fun updateUserInDB(user: UserEntity) = DBManager.updateUser(user)

    fun getUsersListFromDB() = DBManager.getUsersList()

}