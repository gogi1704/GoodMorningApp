package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.goodmorningapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(com.example.goodmorningapp.R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

//        setupActionBarWithNavController(
//            navController, AppBarConfiguration(
//                setOf(
//                    com.example.goodmorningapp.R.id.notes,
//                    com.example.goodmorningapp.R.id.weather,
//                    com.example.goodmorningapp.R.id.money
//                )
//            )
//        )
        binding.bottomNavigation.setupWithNavController(navController)


        val bottomNavigation: NavigationBarView = binding.bottomNavigation
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.example.goodmorningapp.R.id.notes -> {

                    loadFragment(NoteFragment())
                    true
                }
                com.example.goodmorningapp.R.id.weather -> {
                    loadFragment(WeatherFragment())
                    true
                }
                com.example.goodmorningapp.R.id.money -> {
                    loadFragment(MoneyFragment())
                    true
                }
                else -> false
            }
        }


    }

    private fun loadFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(com.example.goodmorningapp.R.id.fragment_container, fragment)
        ft.commit()
    }
}