package com.example.goodmorningapp.data.models.news


data class NewsModel(
    val author:String ,
    val title:String,
    val description:String?,
    val url:String,
    val urlToImage:String?,
    val publishedAt:String,

){

}
