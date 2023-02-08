package com.example.goodmorningapp.ui.adapters.recyclerAdapters

import androidx.recyclerview.widget.DiffUtil
import com.example.goodmorningapp.data.models.NoteModel

class NoteDiffUtil:DiffUtil.ItemCallback<NoteModel>() {
    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem == newItem
    }
}