package com.example.matrimonialapp.repository

import com.example.matrimonialapp.core.Mapper
import com.example.matrimonialapp.db.DBManager
import com.example.matrimonialapp.db.entity.UserEntity
import com.example.matrimonialapp.network.APIClient
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(val dbManager: DBManager) {

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

    fun insertUserInDB(userEntity: UserEntity) = dbManager.insertUser(userEntity)

    fun insertUserListInDB(users: List<UserEntity>) = dbManager.insertUserList(users)

    fun updateUserInDB(user: UserEntity) = dbManager.updateUser(user)

    fun getUsersListFromDB() = dbManager.getUsersList()

}