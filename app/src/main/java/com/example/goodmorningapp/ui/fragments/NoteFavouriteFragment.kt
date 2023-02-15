package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.goodmorningapp.databinding.FragmentNoteFavouriteBinding
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.NoteRecyclerAdapter
import com.example.goodmorningapp.viewModels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFavouriteFragment : Fragment() {
    private lateinit var binding: FragmentNoteFavouriteBinding
    private lateinit var adapter: NoteRecyclerAdapter
    private val noteViewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteFavouriteBinding.inflate(layoutInflater, container, false)
        adapter = NoteRecyclerAdapter(null)
        binding.recyclerFavourite.adapter = adapter

        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }



        noteViewModel.favouriteDataFlow.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }





        return binding.root
    }

}