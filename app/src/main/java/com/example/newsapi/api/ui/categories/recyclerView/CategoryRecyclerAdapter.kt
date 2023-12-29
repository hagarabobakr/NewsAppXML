package com.example.newsapi.api.ui.categories.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.databinding.ItemCategoryBinding

class CategoryRecyclerAdapter(val items : List<Category>):RecyclerView
.Adapter<CategoryRecyclerAdapter.viewHolder>() {
    class viewHolder(val itemBinding:ItemCategoryBinding):RecyclerView
    .ViewHolder(itemBinding.root){
        fun bind(category: Category){
            itemBinding.category = category
            itemBinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       val viewBinding = ItemCategoryBinding
           .inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(viewBinding)
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
//        holder.itemBinding.category = items[position]
//        holder.itemBinding.executePendingBindings()
        holder.bind(items[position])
        onItemClickListner?.let {clickListner->
            holder.itemBinding.container.setOnClickListener{
                clickListner.onItemClick(position,items[position]) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    var onItemClickListner: OnItemClickListner?=null
    interface OnItemClickListner{
        fun onItemClick(pos:Int, item: Category)
    }

}