package com.example.goodmorningapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodmorningapp.data.models.weather.WeatherModel
import com.example.goodmorningapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    application: Application,
    private val weatherRepository: WeatherRepository
) :
    AndroidViewModel(application) {

    var weatherModel: WeatherModel? = null


    init {
        viewModelScope.launch {
            weatherModel = weatherRepository.getWeather()
        }
    }


}