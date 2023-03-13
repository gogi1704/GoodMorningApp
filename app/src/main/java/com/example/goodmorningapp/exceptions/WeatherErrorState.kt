package com.example.goodmorningapp.exceptions

data class WeatherErrorState(
    val isLoad: Boolean = false,
    val errorType:ErrorType? = null
)

sealed class ErrorType{
    object ApiError:ErrorType()
    object NetworkError:ErrorType()
}