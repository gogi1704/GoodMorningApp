package com.example.goodmorningapp.ui.fragments

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.goodmorningapp.databinding.FragmentWeatherBinding
import com.example.goodmorningapp.extensions.getImage
import com.example.goodmorningapp.extensions.isPermissionsGranted
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.weatherAdapter.WeatherAdapter
import com.example.goodmorningapp.viewModels.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var client: FusedLocationProviderClient
    private lateinit var adapter: WeatherAdapter
    private val weatherViewModel: WeatherViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onAttach(context: Context) {
        client = LocationServices.getFusedLocationProviderClient(requireContext())
        weatherViewModel.getWeatherLocal(requireContext(), client)

        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        adapter = WeatherAdapter()
        binding.weatherRecycler.adapter = adapter
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {}
        checkPermissions()

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

            buttonSearch.setOnClickListener {
                if (searchCityEditText.text?.isNotBlank() == true) {
                    weatherViewModel.getWeatherSearch(searchCityEditText.text.toString())
                    searchCityEditText.text!!.clear()

                }
            }
        }


        return binding.root
    }

    private fun checkPermissions() {
        if (!isPermissionsGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isPermissionsGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            pLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
}

