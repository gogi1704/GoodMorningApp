package com.example.goodmorningapp.ui.adapters.recyclerAdapters.newsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodmorningapp.data.models.news.NewsModel
import com.example.goodmorningapp.databinding.RecyclerNewsItemBinding
import com.example.goodmorningapp.extensions.getImage

class NewsRecyclerAdapter() :
    ListAdapter<NewsModel, NewsRecyclerAdapter.NewsViewHolder>(NewsDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            RecyclerNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class NewsViewHolder(private val binding: RecyclerNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsModel) {
            with(binding){
                textTitle.text = item.title
                textNewsContent.text = item.description
                imageNews.getImage(item.urlToImage)
                textPublishedAt.text = item.publishedAt
            }


        }
    }
}