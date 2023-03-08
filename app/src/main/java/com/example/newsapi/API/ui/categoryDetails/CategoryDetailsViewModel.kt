package com.example.newsapi.API.ui.categoryDetails

import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.API.Model.ApiConstants
import com.example.newsapi.API.Model.ApiManager
import com.example.newsapi.API.Model.sourceResponse.Source
import com.example.newsapi.API.Model.sourceResponse.SourceResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsViewModel:ViewModel() {
    val sourceLivedata = MutableLiveData<List<Source?>?>()
    val showErrorLayout = MutableLiveData<String>()
    val showLodingLayout = MutableLiveData<Boolean>()
     fun loadNewsSources( categoryId: String) {
       // showLOdingLayout()
         showLodingLayout.value =true
        ApiManager.
        getApis()
            .getSources(ApiConstants.apiKey,categoryId)
            .enqueue(object : Callback<SourceResponse> {
                override fun onResponse(
                    call: Call<SourceResponse>,
                    response: Response<SourceResponse>,
                ) {
                    //viewBinding.lodingIndicator.isVisible=false
                    showLodingLayout.value = false
                    if(response.isSuccessful){
                        //bindSourcesIntabLayout(response.body()?.sources)
                        sourceLivedata.value = response.body()?.sources

                    }else{
                        val gson = Gson()
                        val errorResponse=
                            gson.fromJson(
                                response.errorBody()?.string(),
                                SourceResponse::class.java)
                        //showErrorLayou(errorResponse.message)
                        showErrorLayout.value = response.body()?.message?:" "
                    }
                }
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    //viewBinding.lodingIndicator.isVisible= false
                    showLodingLayout.value = false
                   // showErrorLayou(t.localizedMessage)
                    showErrorLayout.value = t.localizedMessage
                }

            })
    }
}