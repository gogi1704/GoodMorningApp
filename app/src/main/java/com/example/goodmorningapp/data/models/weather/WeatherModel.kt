package com.example.goodmorningapp.data.models.weather



data class WeatherModel(
   val location:Location,
   val current:CurrentWeather,
   val days:List<WeatherDay>,

)