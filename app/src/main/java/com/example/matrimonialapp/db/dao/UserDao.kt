package com.example.matrimonialapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.matrimonialapp.db.entity.UserEntity


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(entity: List<UserEntity>)
    @Update
    fun update(entity: UserEntity)

    @Query("SELECT * FROM users_list")
    fun getAllEntities(): LiveData<List<UserEntity>>
}