package com.example.goodmorningapp.data.models.weather



data class WeatherModel(
   val location:Location,
   val currentWeather:CurrentWeather,
   val days:List<WeatherDay>

)