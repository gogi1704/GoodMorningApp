package com.example.goodmorningapp.ui.adapters.recyclerAdapters.noteAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.goodmorningapp.data.models.note.NoteModel

class NoteDiffUtil:DiffUtil.ItemCallback<NoteModel>() {
    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem == newItem
    }
}