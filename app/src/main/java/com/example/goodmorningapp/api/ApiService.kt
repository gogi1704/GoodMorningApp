package com.example.goodmorningapp.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

private const val WEATHER_API_KEY = "1f74fa0439d648ebb4f173450231502"
const val NEWS_API_KEY = "484b05f2fc4f4f158f18be170b7796ae"
const val NEWS_BASE_URL = "https://newsapi.org/v2/top-headlines/"


interface ApiService {


    @GET("forecast.json?key=$WEATHER_API_KEY")
    suspend fun getWeather(
        @Query("q") locate: String,
        @Query("days") days: Int,
        @Query("lang") lang: String
    ): Response<JsonObject>

    @GET
    suspend fun newsResponse(
        @Url url: String,
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): Response<JsonObject>


    @GET
    suspend fun getNewsSources(
        @Url url: String,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<JsonObject>

}