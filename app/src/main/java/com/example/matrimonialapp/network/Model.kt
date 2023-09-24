package com.example.matrimonialapp.network

import com.example.matrimonialapp.core.UserStatus
import com.google.gson.annotations.SerializedName

class Model {
    data class Users(
        @SerializedName("results") val results: ArrayList<Results>,
        @SerializedName("info") val info: Info?
    ) {

        data class Results(
            @SerializedName("gender") val gender: String?,
            @SerializedName("name") val name: Name?,
            @SerializedName("location") val location: Location?,
            @SerializedName("email") val email: String?,
            @SerializedName("login") val login: Login?,
            @SerializedName("dob") val dob: Dob?,
            @SerializedName("registered") val registered: Registered?,
            @SerializedName("phone") val phone: String?,
            @SerializedName("cell") val cell: String?,
            @SerializedName("id") val id: Id?,
            @SerializedName("picture") val picture: Picture?,
            @SerializedName("nat") val nat: String?,
            var status: String
        )

        data class Info(
            @SerializedName("seed") val seed: String?,
            @SerializedName("results") val results: String?,
            @SerializedName("page") val page: String?,
            @SerializedName("version") val version: String?
        )

        data class Name(
            @SerializedName("title") val title: String?,
            @SerializedName("first") val first: String?,
            @SerializedName("last") val last: String?
        )

        data class Street(
            @SerializedName("number") val number: String?,
            @SerializedName("name") val name: String?
        )

        data class Coordinates(

            @SerializedName("latitude") val latitude: String?,
            @SerializedName("longitude") val longitude: String?

        )

        data class Timezone(
            @SerializedName("offset") val offset: String?,
            @SerializedName("description") val description: String?
        )

        data class Location(
            @SerializedName("street") val street: Street?,
            @SerializedName("city") val city: String?,
            @SerializedName("state") val state: String?,
            @SerializedName("country") val country: String?,
            @SerializedName("postcode") val postcode: String?,
            @SerializedName("coordinates") val coordinates: Coordinates?,
            @SerializedName("timezone") val timezone: Timezone?
        )

        data class Login(
            @SerializedName("uuid") val uuid: String?,
            @SerializedName("username") val username: String?,
            @SerializedName("password") val password: String?,
            @SerializedName("salt") val salt: String?,
            @SerializedName("md5") val md5: String?,
            @SerializedName("sha1") val sha1: String?,
            @SerializedName("sha256") val sha256: String?
        )

        data class Dob(
            @SerializedName("date") val date: String?,
            @SerializedName("age") val age: String?
        )

        data class Registered(
            @SerializedName("date") val date: String?,
            @SerializedName("age") val age: String?
        )

        data class Id(
            @SerializedName("name") val name: String?,
            @SerializedName("value") val value: String?
        )

        data class Picture(
            @SerializedName("large") val large: String?,
            @SerializedName("medium") val medium: String?,
            @SerializedName("thumbnail") val thumbnail: String?
        )
    }
}