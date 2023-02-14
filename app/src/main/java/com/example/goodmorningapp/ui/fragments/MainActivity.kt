package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.goodmorningapp.R
import com.example.goodmorningapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        val bottomNavigation: NavigationBarView = binding.bottomNavigation
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.notes -> {
                    navController.navigate(R.id.noteFragment)
                    true
                }
                R.id.weather -> {
                    navController.navigate(R.id.weatherFragment)

                    true
                }
                R.id.money -> {
                    navController.navigate(R.id.moneyFragment)

                    true
                }
                else -> false
            }
        }

    }

}