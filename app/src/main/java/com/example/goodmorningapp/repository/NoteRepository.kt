package com.example.goodmorningapp.repository

import com.example.goodmorningapp.data.db.entities.NoteEntity
import com.example.goodmorningapp.data.models.note.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    val dataFlow: Flow<List<NoteModel>>
    val favouriteDataFlow:Flow<List<NoteModel>>
    suspend fun insertNote(noteEntity: NoteEntity)
    suspend fun deleteNote(id: Int)


}