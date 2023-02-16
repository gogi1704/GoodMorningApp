package com.example.goodmorningapp.ui.adapters.recyclerAdapters.weatherAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.goodmorningapp.data.models.weather.WeatherDay

class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherDay>() {
    override fun areItemsTheSame(oldItem: WeatherDay, newItem: WeatherDay): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: WeatherDay, newItem: WeatherDay): Boolean {
        return oldItem == newItem
    }
}