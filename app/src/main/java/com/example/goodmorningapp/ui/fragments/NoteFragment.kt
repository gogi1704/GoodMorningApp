package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.goodmorningapp.R
import com.example.goodmorningapp.data.models.NoteModel
import com.example.goodmorningapp.databinding.FragmentNoteBinding
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.NoteListener
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.NoteRecyclerAdapter
import com.example.goodmorningapp.viewModels.NoteViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class NoteFragment : Fragment() {
    private lateinit var adapter: NoteRecyclerAdapter
    private lateinit var binding: FragmentNoteBinding
    private val noteViewModel: NoteViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        adapter = NoteRecyclerAdapter(object : NoteListener {

            override fun favourite(noteModel: NoteModel) {
                noteViewModel.favourite(noteModel)
            }

        })

        with(binding) {
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                .visibility = View.VISIBLE

            recycler.adapter = adapter
            buttonAddNote.setOnClickListener {
                findNavController().navigate(R.id.action_noteFragment_to_createNoteFragment)
            }
            appBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.favourite -> {
                        findNavController().navigate(R.id.noteFavouriteFragment)
                        true
                    }
                    else -> false
                }
            }
        }



        noteViewModel.dataFlow.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }


        return binding.root
    }




}