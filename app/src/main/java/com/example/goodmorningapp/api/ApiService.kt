package com.example.goodmorningapp.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

private const val WEATHER_API_KEY = "1f74fa0439d648ebb4f173450231502"

interface ApiService {



    @GET("forecast.json?key=$WEATHER_API_KEY")
    suspend fun getWeather(@Query("q") locate: String ,@Query("days") days:Int , @Query("lang") lang:String): Response<JsonObject>

    @GET
    suspend fun getNews(@Url url:String):Response<JsonObject>
}