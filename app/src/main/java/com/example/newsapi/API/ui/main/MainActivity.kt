package com.example.newsapi.API.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.newsapi.R
import com.example.newsapi.API.Model.ApiManager
import com.example.newsapi.API.Model.sourceResponse.SourceResponse
import com.example.newsapi.API.ui.category.CategoryFragment
import com.example.newsapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiKey:String="bf1fff6b9a8f4c40b3617f1f42309a25"
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,CategoryFragment())
            .commit()

    }
}