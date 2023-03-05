package com.example.newsapi.API.ui.categories.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.databinding.ItemCategoryBinding

class CategoryRecyclerAdapter(val items : List<Category>):RecyclerView.Adapter<CategoryRecyclerAdapter.viewHolder>() {
    class viewHolder(val itemBinding:ItemCategoryBinding):RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       val viewBinding = ItemCategoryBinding
           .inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(viewBinding)
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.itemBinding.title.setText(items[position].name)
        holder.itemBinding.container.setCardBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                items[position].backgroundColorId,
            ))

        holder.itemBinding.image.setImageResource(items[position].imageId)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    //interface

}