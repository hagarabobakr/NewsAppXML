package com.example.newsapi.API.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.newsapi.API.Model.ApiConstants
import com.example.newsapi.API.Model.ApiManager
import com.example.newsapi.API.Model.sourceResponse.Source
import com.example.newsapi.API.Model.sourceResponse.SourceResponse
import com.example.newsapi.API.ui.newsFragment.NewsFragment
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentCategoryBinding
import com.google.android.material.tabs.TabLayout
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
        loadNewsSources()


    }
    fun changeNewsFragment(source: Source){
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,NewsFragment.getInstance(source))
    }

    private fun loadNewsSources() {
        showLOdingLayout()
        ApiManager.
        getApis()
            .getSources(ApiConstants.apiKey)
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
                            gson.fromJson(
                                response.errorBody()?.string(),
                                SourceResponse::class.java)
                        showerrorLayou(errorResponse.message)
                    }
                }
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    viewBinding.lodingIndicator.isVisible= false
                    showerrorLayou(t.localizedMessage)
                }

            })
    }

    private fun showLOdingLayout() {
        viewBinding.lodingIndicator.isVisible=true
        viewBinding.trayAgin.isVisible=false
    }

    private fun showerrorLayou(message: String?) {
        viewBinding.lodingIndicator.isVisible=false
        viewBinding.errorLayout.isVisible=true
        viewBinding.errorMessage.text=message

    }

    fun bindSourcesIntabLayout(sourceList:List<Source?>?){
        sourceList?.forEach {
            val tab = viewBinding.tabLayout.newTab()
            tab.text=it?.name
            tab.tag = it
            viewBinding.tabLayout.addTab(tab)
        }
        viewBinding.tabLayout.addOnTabSelectedListener(
            object :TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

}
