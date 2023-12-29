package com.example.newsapi.api.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    val consUrl:String="https://newsapi.org/"
    companion object{
        var retrofit:Retrofit?=null
        private @Synchronized fun getInstance():Retrofit{
            retrofit =Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit!!
        }
        fun getApis(): WebServies {
            return getInstance().create(WebServies::class.java)
        }
    }

}