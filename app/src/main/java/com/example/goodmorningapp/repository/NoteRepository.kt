package com.example.goodmorningapp.repository

import com.example.goodmorningapp.data.db.entities.NoteEntity

interface NoteRepository {
    suspend fun getAll(): List<NoteEntity>
    suspend fun insertNote(noteEntity: NoteEntity)
    suspend  fun deleteNote(id: Int)


}