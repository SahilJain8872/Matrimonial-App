package com.example.matrimonialapp.db

import com.example.matrimonialapp.activity.MainActivity
import com.example.matrimonialapp.db.entity.UserEntity

class DBManager {
    companion object{
        private val database = AppDatabase.getDatabase(MainActivity.appContext)
        private val dao = database?.userDao()

        fun insertUser(user: UserEntity) = dao?.insert(user)

        fun insertUserList(users: List<UserEntity>) = dao?.insertUsers(users)

        fun updateUser(user: UserEntity) = dao?.update(user)

        fun getUsersList() = dao?.getAllEntities()

    }
}