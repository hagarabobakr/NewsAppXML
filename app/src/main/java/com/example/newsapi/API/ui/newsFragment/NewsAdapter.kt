package com.example.newsapi.API.ui.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapi.API.Model.newsResponse.News
import com.example.newsapi.databinding.ItemNewsBinding

class NewsAdapter(var items :List<News?>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(val viewbinding:ItemNewsBinding):RecyclerView.ViewHolder(viewbinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =ItemNewsBinding.
        inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.viewbinding.other.text = item?.author
        holder.viewbinding.time.text = item?.publishedAt
        holder.viewbinding.title.text = item?.title
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.viewbinding.image)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
    fun changeData(articles: List<News?>?) {
        items = articles
        notifyDataSetChanged()

    }

}