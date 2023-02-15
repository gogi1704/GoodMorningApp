package com.example.goodmorningapp.data.models.weather

data class Day(
    val maxtemp_c:String,
    val mintemp_c:String,
    val condition: Condition,
    val hour:List<WeatherHour>
)
