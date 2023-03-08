package com.example.newsapi.API.ui.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    lateinit var viewModel: CategoryDetailsViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //loadNewsSources()
        viewModel.loadNewsSources(category.id)
        subscribeToLiveData()


    }

    private fun subscribeToLiveData() {
        viewModel.sourceLivedata.observe(viewLifecycleOwner, {
            bindSourcesIntabLayout(it)
        })
        viewModel.showLodingLayout.observe(viewLifecycleOwner, { show->
            if (show)
                showLOdingLayout()
            else hideLodinglayout()

        })
        viewModel.showErrorLayout.observe(viewLifecycleOwner, {
            showErrorLayou(it)
        })
    }

    private fun hideLodinglayout() {

        viewBinding.lodingIndicator.isVisible=false
        viewBinding.trayAgin.isVisible=false
    }

    fun changeNewsFragment(source: Source){
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,NewsFragment.getInstance(source))
            .commit()
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
            val layoutParams = LinearLayout.LayoutParams(tab.view.layoutParams)
            layoutParams.marginEnd = 15
            layoutParams.marginStart = 15
            layoutParams.bottomMargin = 8
            layoutParams.topMargin = 8
            tab.view.layoutParams = layoutParams
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
