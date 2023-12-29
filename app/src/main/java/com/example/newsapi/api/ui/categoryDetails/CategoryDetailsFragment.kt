package com.example.newsapi.api.ui.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapi.api.model.sourceResponse.Source
import com.example.newsapi.api.ui.categories.recyclerView.Category
import com.example.newsapi.api.ui.newsFragment.NewsFragment
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentDetailsCategoryBinding
import com.google.android.material.tabs.TabLayout

class CategoryDetailsFragment : Fragment() {
    lateinit var viewBinding: FragmentDetailsCategoryBinding
    lateinit var viewModel : CategoryDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDetailsCategoryBinding.inflate(
            inflater, container, false
        )
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadNewsSources(category.id)
        subscribeToLiveData()
    }

    fun changeNewsFragment(source: Source) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.getInstance(source))
            .commit()

    }



    private fun showLoadingLayout() {
        viewBinding.loadingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }

    private fun showErrorLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loadingIndicator.isVisible = false;
        viewBinding.errorMessage.text = message
    }

    fun bindSourcesInTabLayout(sourcesList: List<Source?>?) {
        sourcesList?.forEach { source ->
            val tab = viewBinding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            viewBinding.tabLayout.addTab(tab)

            (tab.view.layoutParams as LinearLayout.LayoutParams).marginStart = 12
            (tab.view.layoutParams as LinearLayout.LayoutParams).marginEnd = 12
            (tab.view.layoutParams as LinearLayout.LayoutParams).topMargin = 20
            (tab.view.layoutParams as LinearLayout.LayoutParams).bottomMargin = 25
//            val layoutParams = LinearLayout.LayoutParams(tab.view.layoutParams)
//            layoutParams.marginEnd =12
//            layoutParams.marginStart  = 12
//            tab.view.layoutParams = layoutParams

        }
        viewBinding.tabLayout
            .addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }
            })
        viewBinding.tabLayout.getTabAt(0)?.select()
    }

    lateinit var category: Category

    companion object {
        fun getInstance(category: Category): CategoryDetailsFragment {
            val fragment = CategoryDetailsFragment()
            fragment.category = category
            return fragment
        }
    }
    fun subscribeToLiveData(){
        viewModel.sourcesLiveData.observe(viewLifecycleOwner,{
            bindSourcesInTabLayout(it)
        })

        viewModel.showLodingLayout.observe(viewLifecycleOwner,{
            if (it)
                showLoadingLayout()
            else hidLoding()
        })
        viewModel.showErrorLayout.observe(viewLifecycleOwner,{
            showErrorLayout(it)
        })
    }

    private fun hidLoding() {
        viewBinding.loadingIndicator.isVisible = false
    }
}
