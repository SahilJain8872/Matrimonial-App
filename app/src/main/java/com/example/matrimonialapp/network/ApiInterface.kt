package com.example.matrimonialapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/")
    fun getUsers(@Query("results") queryData: String) : Call<Model.Users>

}