package com.example.newsapi.api.model

import com.example.newsapi.api.model.newsResponse.NewsResponse
import com.example.newsapi.api.model.sourceResponse.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServies {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey")apiKey :String,
                   @Query("category") category : String
                   ) :Call<SourceResponse>
    @GET("v2/everything")
    fun getNews(@Query("apiKey") apiKey : String,
                @Query("sources") sources : String,
                @Query("pageSize") pageSize : Int,
                @Query("page") page : Int
    ):Call<NewsResponse>
}