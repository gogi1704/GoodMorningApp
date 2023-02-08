package com.example.goodmorningapp.data.models

data class NoteModel(
    val id:Int,
    val title:String? = null,
    val content:String,
    val published:String,
    val isFavourite:Boolean? = null
)