package com.example.goodmorningapp.ui.adapters.recyclerAdapters.weatherAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodmorningapp.data.models.weather.WeatherDay
import com.example.goodmorningapp.databinding.RecyclerWeatherItemBinding
import com.example.goodmorningapp.extensions.getImage

class WeatherAdapter() :
    ListAdapter<WeatherDay, WeatherAdapter.WeatherViewHolder>(WeatherDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding =
            RecyclerWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class WeatherViewHolder(private val binding: RecyclerWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherDay) {
            with(binding) {
                dateEditText.text = item.date
                conditionText.text = item.day.condition.text
                image.getImage("https:${item.day.condition.icon}")
                tempMaxCountText.text = item.day.maxtemp_c
                tempMinCountText.text = item.day.mintemp_c


            }

        }

    }
}