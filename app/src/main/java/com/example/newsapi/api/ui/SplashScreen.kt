package com.example.newsapi.api.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapi.api.ui.main.MainActivity
import com.example.newsapi.R
import com.example.newsapi.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var viewBinding :ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        with(viewBinding.logo) {
            alpha=0f
            animate().setDuration(1500).alpha(1f).withEndAction{
        val intent = Intent(this@SplashScreen,MainActivity::class.java)
        startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            }}
    }
}