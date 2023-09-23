package com.example.matrimonialapp.network

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object{
        private var retrofit : Retrofit? = null
        private var baseUrl = "https://randomuser.me/"
        @Synchronized
        private fun getClient(): Retrofit = retrofit ?: Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        @Synchronized
        fun getApiInterface() =  getClient().create(ApiInterface::class.java)

    }

}