package com.example.goodmorningapp.data.models.news

data class NewsStateModel(
    val listNewsModel: List<NewsModel> = emptyList(),
    val isLoading:Boolean = false
)
