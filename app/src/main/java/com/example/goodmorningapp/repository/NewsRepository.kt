package com.example.goodmorningapp.repository

import com.example.goodmorningapp.api.ApiService
import com.example.goodmorningapp.api.NEWS_API_KEY
import com.example.goodmorningapp.api.NEWS_BASE_URL
import com.example.goodmorningapp.data.db.dao.NewsSourceDao
import com.example.goodmorningapp.data.models.news.NewsModel
import com.example.goodmorningapp.data.models.news.NewsSourceModel
import com.example.goodmorningapp.data.models.news.toEntity
import com.example.goodmorningapp.data.models.news.toModel
import com.example.goodmorningapp.exceptions.ApiError
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: ApiService,
    private val newsSourceDao: NewsSourceDao
) {
    private val gson = Gson()

    val newsDataFlow = newsSourceDao.getAll()
        .map { it.toModel() }
        .flowOn(Dispatchers.Default)

    suspend fun getNews(source: String): List<NewsModel> {
        val response = api.newsResponse(NEWS_BASE_URL, source, NEWS_API_KEY)
        if (response.isSuccessful) {
            val body = response.body() ?: throw ApiError(response.message())
            return parseNews(body.getAsJsonArray("articles"))
        } else
            throw Exception()
    }

    suspend fun getNewsSources() {
        val response = api.getNewsSources("${NEWS_BASE_URL}sources", "ru", NEWS_API_KEY)
        if (response.isSuccessful) {
            val body = response.body() ?: throw ApiError(response.message())
            val sources = parseNewsSources(body.getAsJsonArray("sources"))
            newsSourceDao.insertNews(sources.toEntity())
        } else throw ApiError(response.message())
    }

    private fun parseNewsSources(jsonObject: JsonArray): List<NewsSourceModel> {
        val sources = gson.fromJson(jsonObject, Array<NewsSourceModel>::class.java)
        return sources.toList()
    }

    private fun parseNews(jsonObject: JsonArray): List<NewsModel> {
        val news = gson.fromJson(jsonObject, Array<NewsModel>::class.java)
        return news.toList()
    }

   suspend fun check(id: String) {
        newsSourceDao.check(id)
    }


}