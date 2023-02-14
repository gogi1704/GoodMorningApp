package com.example.goodmorningapp.data.models

import com.example.goodmorningapp.data.db.entities.NoteEntity

data class NoteModel(
    val id: Int,
    val title: String? = null,
    val content: String,
    val published: String,
    val isFavourite: Boolean? = null
) {
    fun toEntity(): NoteEntity = NoteEntity(id, title, content, published, isFavourite)


    companion object {
        fun fromEntity(noteEntity: NoteEntity): NoteModel = NoteModel(
            noteEntity.id,
            noteEntity.title,
            noteEntity.content,
            noteEntity.published,
            noteEntity.isFavourite
        )
    }
}