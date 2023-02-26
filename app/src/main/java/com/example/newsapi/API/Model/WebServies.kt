package com.example.newsapi.API.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServies {
    @GET("v2/top-headlines/sources?apiKey=API_KEY")
    fun getSources(@Query("apikey")apikey:String) :Call<SourceResponse>
}