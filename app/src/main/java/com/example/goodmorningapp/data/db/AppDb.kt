package com.example.goodmorningapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.goodmorningapp.data.db.dao.NewsSourceDao
import com.example.goodmorningapp.data.db.dao.NoteDao
import com.example.goodmorningapp.data.db.entities.NewsSourceEntity
import com.example.goodmorningapp.data.db.entities.NoteEntity

@Database(entities = [NoteEntity::class , NewsSourceEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun newsSourceDao(): NewsSourceDao
}