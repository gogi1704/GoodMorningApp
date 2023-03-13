package com.example.goodmorningapp.repository

import com.example.goodmorningapp.api.WeatherApiService
import com.example.goodmorningapp.data.models.weather.*
import com.example.goodmorningapp.exceptions.ApiError
import com.example.goodmorningapp.exceptions.NetworkError
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.IOException
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApiService) {
    private val gson = Gson()


    suspend fun getWeather(location:String): WeatherModel {
        try {
            val response = weatherApi.getWeather(location, 3, "ru")
            if (response.isSuccessful) {
                val body = response.body() ?: throw ApiError(response.message())
                println(parseWeather(body))
                return parseWeather(body)
            } else {
                throw ApiError(response.message())
            }
        }catch (e:IOException){
            throw NetworkError()
        }catch (e:Exception){
            throw UnknownError()
        }


    }

    private fun parseWeather(json: JsonObject): WeatherModel {
        val location = parseLocation(json.getAsJsonObject("location"))
        val currentWeather = parseCurrentWeather(json.getAsJsonObject("current"))
        val days = parseDays(json.getAsJsonObject("forecast").getAsJsonArray("forecastday"))
        return WeatherModel(location, currentWeather, days.toList())
    }

    private fun parseLocation(json: JsonObject): Location {
        return gson.fromJson(json, Location::class.java)
    }

    private fun parseCurrentWeather(json: JsonObject): CurrentWeather {
        return gson.fromJson(json, CurrentWeather::class.java)
    }


    private fun parseDays(json: JsonArray): Array<WeatherDay> {
        val days = gson.fromJson(json, Array<WeatherDay>::class.java)
        return days
    }

}