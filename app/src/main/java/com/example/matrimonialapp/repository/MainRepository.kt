package com.example.matrimonialapp.repository

import com.example.matrimonialapp.Response
import com.example.matrimonialapp.network.APIClient
import java.lang.Exception

class MainRepository {

    fun getUsers(queryData: String) = try{
        val result = APIClient.getApiInterface().getUsers(queryData).execute()
        if(result.isSuccessful){
                if (result.body() != null) {
                    Response.success(result.body())
                } else {
                    Response.error(Throwable("unable to fetch users"))
                }
        }else{
           Response.error(Throwable("unable to fetch users"))
        }
    }catch (e: Exception){
        Response.error(Throwable("unable to fetch users"))
    }

}