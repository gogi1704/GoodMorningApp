package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.goodmorningapp.databinding.FragmentWeatherBinding
import com.example.goodmorningapp.extensions.getImage
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.weatherAdapter.WeatherAdapter
import com.example.goodmorningapp.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding

    private lateinit var adapter: WeatherAdapter
    private val weatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        adapter = WeatherAdapter()
        binding.weatherRecycler.adapter = adapter
        with(binding) {


            weatherViewModel.weatherLivedata.observe(viewLifecycleOwner) {
                if (it != null) {
                    image.getImage("https:${it.days[0].day.condition.icon}")
                    cityEditText.text = it.location.name
                    dateEditText.text = it.location.localtime
                    tempCountText.text = it.current.temp_c
                    feelsLikeText.text = it.current.feelslike_c
                    lastUpdateText.text = it.current.last_updated

                    windText.text = it.current.wind_kph
                    humidityText.text = it.current.humidity
                    cloudText.text = it.current.cloud

                    adapter.submitList(it.days)
                }
            }
        }









        return binding.root
    }

}