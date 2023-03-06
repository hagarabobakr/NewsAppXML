package com.example.newsapi.API.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapi.API.ui.categories.recyclerView.Category
import com.example.newsapi.API.ui.categories.recyclerView.CategoryRecyclerAdapter
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentCategoriesBinding
import com.example.newsapi.databinding.ItemCategoryBinding


class CategoriesFragment : Fragment() {
    lateinit var viewBinding: FragmentCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return viewBinding.root
    }
    val categoryAdapter = CategoryRecyclerAdapter(Category.getCategoryList())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.categoryRecycler.adapter=categoryAdapter
        categoryAdapter.onItemClickListner=object :CategoryRecyclerAdapter
        .OnItemClickListner{
            override fun onItemClick(pos: Int, item: Category) {
                onCategoryClickListener?.onCategoryClick(item)
            }

        }
    }
    var onCategoryClickListener: OnCategoryClickListener? = null

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category)
    }


}