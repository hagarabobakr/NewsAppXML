package com.example.newsapi.api.ui.categoryDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.api.model.ApiConstants
import com.example.newsapi.api.model.ApiManager
import com.example.newsapi.api.model.sourceResponse.Source
import com.example.newsapi.api.model.sourceResponse.SourceResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsViewModel:ViewModel() {
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val showLodingLayout = MutableLiveData<Boolean>()
    val showErrorLayout = MutableLiveData<String>()

     fun loadNewsSources(categoryId : String) {
        //showLoadingLayout()
        showLodingLayout.value = true
        ApiManager
            .getApis()
            .getSources(ApiConstants.apiKey, categoryId)
            .enqueue(object : Callback<SourceResponse> {
                override fun onResponse(
                    call: Call<SourceResponse>,
                    response: Response<SourceResponse>
                ) {
                   //viewBinding.loadingIndicator.isVisible = false
                    showLodingLayout.value = false
                    if (response.isSuccessful) {
                        //bindSourcesInTabLayout(response.body()?.sources)
                        sourcesLiveData.value = response.body()?.sources
                    } else {
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(
                                response.errorBody()?.string(),
                                SourceResponse::class.java
                            );
                        //showErrorLayout(errorResponse.message);
                        showErrorLayout.value = errorResponse.message?:" "
                    }
                }

                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    //viewBinding.loadingIndicator.isVisible = false
                    showLodingLayout.value = false
                    //showErrorLayout(t.localizedMessage);
                    showErrorLayout.value = t.localizedMessage?:" "
                }
            })
    }
}