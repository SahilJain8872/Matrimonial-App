package com.example.matrimonialapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.matrimonialapp.db.dao.UserDao
import com.example.matrimonialapp.db.entity.UserEntity


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao

}