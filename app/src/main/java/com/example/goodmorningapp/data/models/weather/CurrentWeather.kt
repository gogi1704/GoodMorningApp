package com.example.goodmorningapp.data.models.weather

data class CurrentWeather(
    val last_updated: String,
    val temp_c: String,
    val condition: Condition
)
