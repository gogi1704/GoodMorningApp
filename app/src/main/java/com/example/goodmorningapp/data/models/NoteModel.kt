package com.example.goodmorningapp.data.models

import com.example.goodmorningapp.data.db.entities.NoteEntity

data class NoteModel(
    val id: Int = 0,
    val title: String? = null,
    val content: String,
    val published: String,
    val isFavourite: Boolean = false
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

fun List<NoteModel>.toEntity(): List<NoteEntity> = map { it.toEntity() }

fun List<NoteEntity>.toModel(): List<NoteModel> = map { NoteModel.fromEntity(it) }