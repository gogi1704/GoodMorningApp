package com.example.goodmorningapp.data.db.dao

import androidx.room.*
import com.example.goodmorningapp.data.db.entities.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
   suspend fun getAll(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertNote(noteEntity: NoteEntity)

    @Query("DELETE FROM NoteEntity WHERE id = :id")
  suspend  fun deleteNote(id: Int)
}