package com.example.goodmorningapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsSourceEntity(
    @PrimaryKey
    val id:String,
    val name:String ,
    val description:String,
    val url:String,
    val category:String,
    val language:String,
    val country:String,
    val isUsed:Boolean = false
)