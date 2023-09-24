package com.example.matrimonialapp.core

import com.example.matrimonialapp.db.entity.UserEntity
import com.example.matrimonialapp.network.Model

class Mapper {
    companion object{
        fun getUserEntityFromUsers(users: Model.Users): List<UserEntity>{
            val list = arrayListOf<UserEntity>()
            for(user in users.results){
                list.add(UserEntity(
                    id = user.login?.uuid ?: "",
                    firstName = user.name?.first ?: "",
                    lastName = user.name?.last ?: "",
                    age = user.dob?.age ?:  "",
                    gender = user.gender ?: "",
                    city = user.location?.city ?: "",
                    state = user.location?.state ?: "",
                    country = user.location?.country ?: "",
                    postcode = user.location?.postcode ?: "",
                    email = user.email ?: "",
                    status = UserStatus.NONE.toString(),
                    picture = user.picture?.large ?: ""
                ))
            }
            return list
        }

        fun getUsersFromUserEntity(userEntityList: List<UserEntity>): Model.Users{
            val list = arrayListOf<Model.Users.Results>()
            for(user in userEntityList){
                list.add(
                    Model.Users.Results(
                        gender = user.gender,
                        name = Model.Users.Name(title = "", first = user.firstName, last = user.lastName),
                        location = Model.Users.Location(street = null,city = user.city,country = user.country, postcode = user.postcode,coordinates = null, state = user.state, timezone = null),
                        email = user.email,
                        login = Model.Users.Login(user.id,"","","","","",""),
                        dob = Model.Users.Dob(date = "", age = user.age),
                        registered = null,
                        phone = null,
                        cell = null,
                        id = null,
                        picture = Model.Users.Picture(user.picture,"",""),
                        nat = null,
                ))
            }
            return Model.Users(list,null)
        }
    }
}