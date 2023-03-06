package com.example.newsapi.API.ui.categoryDetails

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
import com.example.newsapi.API.ui.categories.CategoriesFragment
import com.example.newsapi.API.ui.categories.recyclerView.Category
import com.example.newsapi.API.ui.newsFragment.NewsFragment
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentDetailsCategoryBinding
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsFragment:Fragment(){
    lateinit var category:Category
    companion object {
        fun getInstance(category: Category): CategoryDetailsFragment {
            val fragment = CategoryDetailsFragment()
            fragment.category = category
            return fragment
        }
    }
    lateinit var viewBinding:FragmentDetailsCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDetailsCategoryBinding
            .inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNewsSources()


    }
    fun changeNewsFragment(source: Source){
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,NewsFragment.getInstance(source))
            .commit()
    }

    private fun loadNewsSources() {
        showLOdingLayout()
        ApiManager.
        getApis()
            .getSources(ApiConstants.apiKey,category.id)
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
                        showErrorLayou(errorResponse.message)
                    }
                }
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    viewBinding.lodingIndicator.isVisible= false
                    showErrorLayou(t.localizedMessage)
                }

            })
    }

    private fun showLOdingLayout() {
        viewBinding.lodingIndicator.isVisible=true
        viewBinding.trayAgin.isVisible=false
    }

    private fun showErrorLayou(message: String?) {
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
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

            }
        )
        viewBinding.tabLayout.getTabAt(0)?.select()
    }

}
