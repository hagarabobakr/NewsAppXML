package com.example.newsapi.API.Model

import com.example.newsapi.API.Model.newsResponse.NewsResponse
import com.example.newsapi.API.Model.sourceResponse.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServies {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey")apiKey:String,
                   @Query("category") category: String
                   ) :Call<SourceResponse>
    @GET("v2/everything")
    fun getNews(@Query("apiKey") apiKey: String,
        @Query("sources") sources: String):Call<NewsResponse>
}