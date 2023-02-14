package com.example.goodmorningapp.data.db.dao

import androidx.room.*
import com.example.goodmorningapp.data.db.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getAll(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertNote(noteEntity: NoteEntity)

    @Query("DELETE FROM NoteEntity WHERE id = :id")
  suspend  fun deleteNote(id: Int)
}