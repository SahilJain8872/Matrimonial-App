package com.example.matrimonialapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_list")
data class UserEntity (
    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "age")
    var age: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "state")
    var state: String,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "postcode")
    var postcode: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "status")
    var status: String
)