package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.goodmorningapp.R
import com.example.goodmorningapp.data.models.note.NoteModel
import com.example.goodmorningapp.databinding.FragmentNoteBinding
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.NoteListener
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.NoteRecyclerAdapter
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.SwipeToDeleteCallback
import com.example.goodmorningapp.viewModels.NoteViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        val swipeCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.layoutPosition
                val id = noteViewModel.dataFlow.value!![position].id
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(R.string.Are_you_sure)
                    .setIcon(R.drawable.ic_baseline_delete_forever_24)
                    .setMessage(R.string.Confirm_deleting_the_note)
                    .setPositiveButton(R.string.Delete) { _, _ ->
                        noteViewModel.delete(id)
                    }
                    .setNegativeButton(R.string.Back) { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
                binding.recycler.adapter?.notifyDataSetChanged()
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.recycler)

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