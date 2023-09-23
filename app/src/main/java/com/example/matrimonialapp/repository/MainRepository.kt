package com.example.matrimonialapp.repository

import com.example.matrimonialapp.network.APIClient

class MainRepository {

    fun getUsers(queryData: String) = APIClient.getApiInterface().getUsers(queryData)


}