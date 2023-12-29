package com.example.newsapi.api.ui.newsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.api.model.ApiConstants
import com.example.newsapi.api.model.ApiManager
import com.example.newsapi.api.model.newsResponse.News
import com.example.newsapi.api.model.newsResponse.NewsResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val newsLiveData = MutableLiveData<List<News?>?>()
    val showLodingLayout = MutableLiveData<Boolean>()
    val showErrorLayout = MutableLiveData<String>()

     fun getNews(sourceId : String, pageSize : Int, page : Int) {
        //showLOdingLayout()
         showLodingLayout.value = true
        ApiManager.getApis()
            .getNews(ApiConstants.apiKey, sources = sourceId, pageSize = pageSize, page = page)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>,
                ) {
                    //viewBinding.lodingIndicator.isVisible=false
                    showLodingLayout.value = false
                    if(response.isSuccessful){
                        //we have news to show
                        //bindNewslist(response.body()?.articles)
                       newsLiveData.value = response.body()?.articles
                    }else{
                        val gson = Gson()
                        val errorResponse=
                            gson.fromJson(
                                response.errorBody()?.string(),
                                NewsResponse::class.java)
                        //showErrorLayou(errorResponse.message)
                        showErrorLayout.value = errorResponse.message?:" "
                    }

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    //viewBinding.lodingIndicator.isVisible= false
                    showLodingLayout.value = false
                    //showErrorLayou(t.localizedMessage)
                    showErrorLayout.value = t.localizedMessage?:" "

                }

            })
    }
}