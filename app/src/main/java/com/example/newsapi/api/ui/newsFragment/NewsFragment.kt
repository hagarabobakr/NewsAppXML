package com.example.newsapi.api.ui.newsFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.api.model.newsResponse.News
import com.example.newsapi.api.model.sourceResponse.Source
import com.example.newsapi.api.ui.newsDetails.NewsDetailsActivity
import com.example.newsapi.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    lateinit var source: Source
    var pageSize = 20
    var currentPage = 1
    var isLoading = false
    companion object{
        fun getInstance(source: Source):NewsFragment{
            val newNewsFragment = NewsFragment()
            newNewsFragment.source= source
            return newNewsFragment
        }
    }
    lateinit var viewBinding: FragmentNewsBinding
    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNews()
        initRecyclerView()
        subscribeToLiveData()

    }

    private fun getNews() {
        viewModel.getNews(source.id ?:"", pageSize = pageSize, page = currentPage)
        isLoading = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
    }
    val newAdapter = NewsAdapter(null)
    private fun initRecyclerView() {
        viewBinding.newsResycler.adapter = newAdapter
        viewBinding.newsResycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                val visibleThreshold = 3
                if (!isLoading&&totalItemCount - lastVisibleItemPosition <= visibleThreshold){
                    isLoading = true
                    currentPage++
                    getNews()
                }
            }
        })
        newAdapter.onNewsClicked = OnItemNewsClick {
            val intent = Intent(requireContext(),NewsDetailsActivity::class.java)
            intent.putExtra("news",it)
            startActivity(intent)
        }

    }



    private fun bindNewslist(articles: List<News?>?) {
       newAdapter.changeData(articles)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater,container,false)
        return  viewBinding.root
    }
    private fun showLodingLayout() {
        viewBinding.lodingIndicator.isVisible=true
        viewBinding.trayAgin.isVisible=false
    }

    private fun showErrorLayout(message: String?) {
        viewBinding.lodingIndicator.isVisible=false
        viewBinding.errorLayout.isVisible=true
        viewBinding.errorMessage.text=message

    }
    private fun subscribeToLiveData(){
        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            bindNewslist(it)
        }

        viewModel.showLodingLayout.observe(viewLifecycleOwner) {
            if (it)
                showLodingLayout()
            else hidLoding()
        }
        viewModel.showErrorLayout.observe(viewLifecycleOwner) {
            showErrorLayout(it)
        }
    }

    private fun hidLoding() {
        viewBinding.lodingIndicator.isVisible = false
    }


}