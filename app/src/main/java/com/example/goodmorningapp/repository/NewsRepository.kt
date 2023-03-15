package com.example.goodmorningapp.repository

import com.example.goodmorningapp.api.ApiService
import com.example.goodmorningapp.data.models.news.NewsModel
import com.example.goodmorningapp.exceptions.ApiError
import com.google.gson.Gson
import com.google.gson.JsonArray
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: ApiService) {
    private val gson = Gson()

    suspend fun getNewsRbc(url: String):List<NewsModel> {
        val response = api.getNews(url)
        if (response.isSuccessful) {
            val body = response.body() ?: throw ApiError(response.message())
            return parseNews(body.getAsJsonArray("articles"))
        } else
           throw Exception()
    }

    private fun parseNews(jsonObject: JsonArray): List<NewsModel> {
        val news = gson.fromJson(jsonObject, Array<NewsModel>::class.java)
        return news.toList()
    }
}