package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goodmorningapp.data.models.NoteModel
import com.example.goodmorningapp.databinding.FragmentNoteBinding
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.NoteRecyclerAdapter

class NoteFragment : Fragment() {
    private lateinit var adapter: NoteRecyclerAdapter
    private lateinit var binding: FragmentNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        adapter = NoteRecyclerAdapter()
        binding.recycler.adapter = adapter

        adapter.submitList(
            listOf(
                NoteModel(0, "title", "content", "22.22.22"),
                NoteModel(0, "title", "content NoteModel(0 , \"title\"         <item name=\"colorPrimary\">@color/md_theme_dark_primary</item>\n        <item name=\"colorPrimary\">@color/md_theme_dark_primary</item>\n, \"content\" , \"22.22.22\")", "22.22.22"),
                NoteModel(0, "title", "content", "22.22.22"),
                NoteModel(0, "title", "content", "22.22.22"),
                NoteModel(0, "title", "content", "22.22.22"),
                NoteModel(0, "title", "content NoteModel(0 , \"title\" , \"content\" , \"22.22.22\")", "22.22.22"),
                NoteModel(0, "title", "content", "22.22.22")
            )
        )






        return binding.root
    }

}