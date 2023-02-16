package com.example.goodmorningapp.ui.adapters.recyclerAdapters.noteAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodmorningapp.data.models.note.NoteModel
import com.example.goodmorningapp.databinding.RecyclerNoteItemBinding
import com.example.goodmorningapp.extensions.parseDateTime

interface NoteListener {
    fun favourite(noteModel: NoteModel)
}

class NoteRecyclerAdapter(
    private val listener: NoteListener?,
) :
    ListAdapter<NoteModel, NoteRecyclerAdapter.NoteViewHolder>(NoteDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            RecyclerNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class NoteViewHolder(
        private val binding: RecyclerNoteItemBinding,
        private val listener: NoteListener?,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NoteModel) {
            with(binding) {
                textTitle.text = item.title ?: ""
                textContent.text = item.content
                textDate.parseDateTime(item.published)
                buttonFavourite.isChecked = item.isFavourite
                buttonFavourite.setOnClickListener {
                    listener?.favourite(item)
                }


            }
        }
    }
}

