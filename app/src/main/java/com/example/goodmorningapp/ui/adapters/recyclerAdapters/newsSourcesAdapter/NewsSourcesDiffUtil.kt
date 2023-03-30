package com.example.goodmorningapp.ui.adapters.recyclerAdapters.newsSourcesAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.goodmorningapp.data.models.news.NewsSourceModel

class NewsSourcesDiffUtil: DiffUtil.ItemCallback<NewsSourceModel>() {
    override fun areItemsTheSame(oldItem: NewsSourceModel, newItem: NewsSourceModel): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsSourceModel, newItem: NewsSourceModel): Boolean {
        return oldItem == newItem
    }
}