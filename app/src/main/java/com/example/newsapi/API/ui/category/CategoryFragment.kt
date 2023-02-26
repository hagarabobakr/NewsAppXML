package com.example.newsapi.API.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapi.API.Model.ApiManager
import com.example.newsapi.API.Model.Source
import com.example.newsapi.API.Model.SourceResponse
import com.example.newsapi.databinding.FragmentCategoryBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment:Fragment(){
    lateinit var viewBinding:FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCategoryBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //loadNewsSources()
    }

    private fun loadNewsSources() {
        ApiManager.getApis().getSources("bf1fff6b9a8f4c40b3617f1f42309a25")
            .enqueue(object : Callback<SourceResponse> {
                override fun onResponse(
                    call: Call<SourceResponse>,
                    response: Response<SourceResponse>,
                ) {
                    viewBinding.lodingIndicator.isVisible=false
                    if(response.isSuccessful){
                    bindSourcesIntabLayout(response.body()?.sources)
                    }else{
                        val gson = Gson()
                        val errorResponse=
                            gson.fromJson(response.errorBody()?.string(),SourceResponse::class.java)
                        errorResponse.message


                    }
                }

                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    viewBinding.lodingIndicator.isVisible=false
                }

            })
    }
    fun bindSourcesIntabLayout(sourceList:List<Source?>){}
}
