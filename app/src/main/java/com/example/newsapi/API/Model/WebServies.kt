package com.example.newsapi.API.Model

import com.example.newsapi.API.Model.newsResponse.NewsResponse
import com.example.newsapi.API.Model.sourceResponse.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServies {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey")apiKey:String) :Call<SourceResponse>
    @GET("v2/everything?q=bitcoin&apiKey=bf1fff6b9a8f4c40b3617f1f42309a25")
    fun getNews(@Query("apiKey") apiKey: String,
        @Query("sources") sources: String):Call<NewsResponse>
}