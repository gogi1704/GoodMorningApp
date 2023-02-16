package com.example.goodmorningapp.data.models.weather

data class CurrentWeather(
    val last_updated: String,
    val temp_c: String,
    val feelslike_c:String,
    val wind_kph:String,
    val humidity:String,
    val cloud:String,
    val condition: Condition
)
