package com.example.goodmorningapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.goodmorningapp.data.db.dao.NoteDao
import com.example.goodmorningapp.data.db.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}