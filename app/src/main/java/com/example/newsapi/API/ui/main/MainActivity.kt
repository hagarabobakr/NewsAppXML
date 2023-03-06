package com.example.newsapi.API.ui.main
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.newsapi.API.ui.categories.CategoriesFragment
import com.example.newsapi.API.ui.categories.recyclerView.Category
import com.example.newsapi.API.ui.categoryDetails.CategoryDetailsFragment
import com.example.newsapi.API.ui.settings.SettingsFragment
import com.example.newsapi.R
import com.example.newsapi.databinding.ActivityMainBinding


@Suppress("DEPRECATION")
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
        categoriesFragment.onCategoryClickListener = this
        ShowCategoriesFragment()

        val toggle = ActionBarDrawerToggle(this,
            viewBinding.root,
            viewBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        viewBinding.root.addDrawerListener(toggle)
        toggle.syncState()
        viewBinding.navView.setNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.nav_categories ->{
                    ShowCategoriesFragment()
                }
                R.id.nav_settings ->{
                    ShowSettingsFragment()
                }
            }
            viewBinding.root.closeDrawers()
            return@setNavigationItemSelectedListener true
        }

    }

    private fun ShowSettingsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main,SettingsFragment())
            .addToBackStack(null)
            .commit()

    }

    private fun ShowCategoriesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main,CategoriesFragment())
            .addToBackStack(null)
            .commit()
    }

    var onCategoryClickListner:OnCategoryClickListner?=null
    interface OnCategoryClickListner{
        fun onCategoryClick(category:Category){

        }
    }


}