package com.example.matrimonialapp.db

import com.example.matrimonialapp.activity.MainActivity
import com.example.matrimonialapp.db.dao.UserDao
import com.example.matrimonialapp.db.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBManager @Inject constructor(val dao: UserDao) {

    fun insertUser(user: UserEntity) = dao.insert(user)

    fun insertUserList(users: List<UserEntity>) = dao.insertUsers(users)

    fun updateUser(user: UserEntity) = dao.update(user)

    fun getUsersList() = dao.getAllEntities()

}