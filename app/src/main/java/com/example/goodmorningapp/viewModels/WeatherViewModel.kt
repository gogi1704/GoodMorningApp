package com.example.goodmorningapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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

    private var weatherModel: WeatherModel? = null
        set(value) {
            field = value
            _weatherLivedata.value = value
        }

    private val _weatherLivedata = MutableLiveData(weatherModel)

    val weatherLivedata
        get() = _weatherLivedata


    init {
        viewModelScope.launch {
            weatherModel = weatherRepository.getWeather()
        }
    }


}