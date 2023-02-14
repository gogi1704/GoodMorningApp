package com.example.goodmorningapp.repository

import com.example.goodmorningapp.data.db.dao.NoteDao
import com.example.goodmorningapp.data.db.entities.NoteEntity
import com.example.goodmorningapp.data.models.NoteModel
import com.example.goodmorningapp.data.models.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao) : NoteRepository {

    override val dataFlow: Flow<List<NoteModel>> = dao.getAll()
        .map { it.toModel() }
        .flowOn(Dispatchers.Default)

    override suspend fun insertNote(noteEntity: NoteEntity) {
        dao.insertNote(noteEntity)
    }

    override suspend fun deleteNote(id: Int) {
        dao.deleteNote(id)
    }
}