package com.example.newsapi.API.ui.newsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.API.Model.ApiConstants
import com.example.newsapi.API.Model.ApiManager
import com.example.newsapi.API.Model.newsResponse.News
import com.example.newsapi.API.Model.newsResponse.NewsResponse
import com.example.newsapi.API.Model.sourceResponse.Source
import com.example.newsapi.API.Model.sourceResponse.SourceResponse
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentNewsBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    lateinit var source: Source
    var pageSize = 20
    var page = 1
    companion object{
        fun getInstance(source: Source):NewsFragment{
            val newNewsFragment = NewsFragment()
            newNewsFragment.source= source
            return newNewsFragment
        }
    }
    lateinit var viewBinding: FragmentNewsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNews()
        initRecyclerView()

    }
    val newAdapter = NewsAdapter(null)
    private fun initRecyclerView() {
        viewBinding.newsResycler.adapter = newAdapter
        viewBinding.newsResycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun getNews() {
        showLOdingLayout()
        ApiManager.getApis()
            .getNews(ApiConstants.apiKey,source.id?:" ", pageSize = pageSize, page = page)
            .enqueue(object :Callback<NewsResponse>{
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>,
                ) {
                    viewBinding.lodingIndicator.isVisible=false
                    if(response.isSuccessful){
                        //we have news to show
                        bindNewslist(response.body()?.articles)
                    }else{
                        val gson = Gson()
                        val errorResponse=
                            gson.fromJson(
                                response.errorBody()?.string(),
                                NewsResponse::class.java)
                        showErrorLayou(errorResponse.message)
                    }

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    viewBinding.lodingIndicator.isVisible= false
                    showErrorLayou(t.localizedMessage)

                }

            })
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
    private fun showLOdingLayout() {
        viewBinding.lodingIndicator.isVisible=true
        viewBinding.trayAgin.isVisible=false
    }

    private fun showErrorLayou(message: String?) {
        viewBinding.lodingIndicator.isVisible=false
        viewBinding.errorLayout.isVisible=true
        viewBinding.errorMessage.text=message

    }


}