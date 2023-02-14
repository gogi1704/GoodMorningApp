package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.goodmorningapp.R
import com.example.goodmorningapp.data.models.NoteModel
import com.example.goodmorningapp.databinding.FragmentCreateNoteBinding
import com.example.goodmorningapp.viewModels.NoteViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CreateNoteFragment : Fragment() {
    private val noteViewModel: NoteViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_createNoteFragment_to_noteFragment)
        }
    }

    private lateinit var binding: FragmentCreateNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        val bottom = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom.visibility = View.GONE

        with(binding) {
            toolBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            buttonCreate.setOnClickListener {
                val note = NoteModel(
                    title = titleEditText.text.toString(),
                    content = contentEditText.text.toString(),
                    published = Calendar.getInstance(Locale("ru", "Ru")).time.toString()
                )
                noteViewModel.addNote(note)
                findNavController().navigateUp()
            }

        }








        return binding.root
    }


}