package com.example.newsapi.api.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newsapi.api.ui.categories.CategoriesFragment
import com.example.newsapi.api.ui.categories.recyclerView.Category
import com.example.newsapi.api.ui.categoryDetails.CategoryDetailsFragment
import com.example.newsapi.api.ui.settings.SettingsFragment
import com.example.newsapi.R
import com.example.newsapi.databinding.ActivityMainBinding


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(),
    CategoriesFragment.OnCategoryClickListener {
    override fun onCategoryClick(category: Category) {
        showCategoriesDetailsFragment(category)
    }

    private fun showCategoriesDetailsFragment(category: Category) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_main, CategoryDetailsFragment
                    .getInstance(category)
            )
            .addToBackStack(null)
            .commit()

    }

    lateinit var viewBinding: ActivityMainBinding
    val categoriesFragment = CategoriesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // viewBinding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(viewBinding.root)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main, categoriesFragment)
            .commit()
        categoriesFragment.onCategoryClickListener = this

        val toggle = ActionBarDrawerToggle(
            this,
            viewBinding.drawer,
            viewBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        viewBinding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        viewBinding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_categories -> {
                    ShowCategoriesFragment()
                }
                R.id.nav_settings -> {
                    ShowSettingsFragment()
                }
            }
            viewBinding.drawer.closeDrawers()
            return@setNavigationItemSelectedListener true
        }

    }

    private fun ShowSettingsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main, SettingsFragment())
            .addToBackStack(null)
            .commit()

    }

    private fun ShowCategoriesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main, CategoriesFragment())
            .addToBackStack(null)
            .commit()
    }

    var onCategoryClickListner: OnCategoryClickListner? = null

    interface OnCategoryClickListner {
        fun onCategoryClick(category: Category) {

        }
    }


}