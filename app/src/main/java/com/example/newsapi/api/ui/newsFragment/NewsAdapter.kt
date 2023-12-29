package com.example.newsapi.api.ui.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.api.model.newsResponse.News
import com.example.newsapi.databinding.ItemNewsBinding

class NewsAdapter(var items :List<News?>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var onNewsClicked: OnItemNewsClick? = null
    class ViewHolder(val viewbinding:ItemNewsBinding):RecyclerView.ViewHolder(viewbinding.root){
        fun bind(news: News? ){
            viewbinding.news = news
            viewbinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =ItemNewsBinding.
        inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item)
        if (onNewsClicked != null){
            holder.itemView.rootView.setOnClickListener {
                onNewsClicked?.onItemNewsClicked(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
    fun changeData(articles: List<News?>?) {
        items = articles
        notifyDataSetChanged()

    }

}