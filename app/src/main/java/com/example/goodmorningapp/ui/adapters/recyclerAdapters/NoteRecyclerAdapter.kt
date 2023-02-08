package com.example.goodmorningapp.ui.adapters.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodmorningapp.data.models.NoteModel
import com.example.goodmorningapp.databinding.RecyclerNoteItemBinding

class NoteRecyclerAdapter() :
    ListAdapter<NoteModel, NoteRecyclerAdapter.NoteViewHolder>(NoteDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            RecyclerNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class NoteViewHolder(private val binding: RecyclerNoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NoteModel) {
            with(binding) {
                textTitle.text = item.title ?: ""
                textContent.text = item.content
                textDate.text = item.published
                buttonFavourite.setOnClickListener {

                }
            }
        }
    }
}