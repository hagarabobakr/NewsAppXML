package com.example.newsapi.api.ui.newsDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.newsapi.R
import com.example.newsapi.api.model.newsResponse.News
import com.example.newsapi.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityNewsDetailsBinding
    lateinit var news: News
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        setContentView(viewBinding.root)

        news = intent.getSerializableExtra("news") as News
        viewBinding.newsD = news
    }
}