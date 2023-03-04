package com.example.newsapi.API.ui.newsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.newsapi.API.Model.ApiConstants
import com.example.newsapi.API.Model.ApiManager
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
    companion object{
        fun getInstance(source: Source):NewsFragment{
            val newNewsFragment = NewsFragment()
            newNewsFragment.source= source
            return newNewsFragment
        }
    }
    lateinit var viewBinding: FragmentNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getNews()

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater,container,false)
        return  viewBinding.root
    }



}