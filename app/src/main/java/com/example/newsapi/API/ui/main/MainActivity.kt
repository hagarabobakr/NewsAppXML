package com.example.newsapi.API.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapi.API.ui.categories.CategoriesFragment
import com.example.newsapi.API.ui.categories.recyclerView.Category
import com.example.newsapi.R
import com.example.newsapi.API.ui.categoryDetails.CategoryDetailsFragment
import com.example.newsapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,
CategoriesFragment.OnCategoryClickListener{
    override fun onCategoryClick(category: Category) {
        showCategoriesDetailsFragment(category)
    }

    private fun showCategoriesDetailsFragment(category: Category) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main,CategoryDetailsFragment
                .getInstance(category))
            .addToBackStack(null)
            .commit()

    }

    lateinit var viewBinding: ActivityMainBinding
    val categoriesFragment = CategoriesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main,categoriesFragment)
            .commit()

    }
    var onCategoryClickListner:OnCategoryClickListner?=null
    interface OnCategoryClickListner{
        fun onCategoryClick(category:Category){

        }
    }


}