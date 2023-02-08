package com.example.goodmorningapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String? = null,
    val content: String,
    val published: String,
    val isFavourite: Boolean? = null
)
