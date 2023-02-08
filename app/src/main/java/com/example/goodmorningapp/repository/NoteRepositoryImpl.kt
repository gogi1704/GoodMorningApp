package com.example.goodmorningapp.repository

import com.example.goodmorningapp.data.db.dao.NoteDao
import com.example.goodmorningapp.data.db.entities.NoteEntity
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao):NoteRepository {
    override suspend fun getAll(): List<NoteEntity> {
     return  dao.getAll()
    }

    override suspend fun insertNote(noteEntity: NoteEntity) {
      dao.insertNote(noteEntity)
    }

    override suspend fun deleteNote(id: Int) {
       dao.deleteNote(id)
    }
}