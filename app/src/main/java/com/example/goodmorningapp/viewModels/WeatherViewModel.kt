package com.example.goodmorningapp.viewModels

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodmorningapp.data.models.weather.WeatherModel
import com.example.goodmorningapp.repository.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.CancellationTokenSource
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

    @RequiresApi(Build.VERSION_CODES.S)
    fun getWeatherLocal(context: Context, client: FusedLocationProviderClient) {

        val token = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        client.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, token.token)
            .addOnCompleteListener {
                viewModelScope.launch {
                    weatherModel =
                        weatherRepository.getWeather("${it.result.latitude},${it.result.longitude}")
                }

            }


    }

    fun getWeatherSearch(city: String){
        viewModelScope.launch {
            weatherModel =
                weatherRepository.getWeather(city)
        }
    }


}