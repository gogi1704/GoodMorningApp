package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goodmorningapp.R
import com.example.goodmorningapp.databinding.FragmentMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        val bottomNavigation: NavigationBarView = binding.bottomNavigation
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.notes -> true
                R.id.weather -> true
                R.id.money -> true
                else -> false
            }
        }


        return binding.root
    }


}