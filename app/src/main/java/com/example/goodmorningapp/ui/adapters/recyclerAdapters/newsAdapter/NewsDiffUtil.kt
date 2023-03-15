package com.example.goodmorningapp.ui.adapters.recyclerAdapters.newsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.goodmorningapp.data.models.news.NewsModel

class NewsDiffUtil: DiffUtil.ItemCallback<NewsModel>() {
    override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
        return  oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
        return oldItem == newItem
    }
}