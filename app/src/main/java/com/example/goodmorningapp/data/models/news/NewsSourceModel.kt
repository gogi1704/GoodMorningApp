package com.example.goodmorningapp.data.models.news

import com.example.goodmorningapp.data.db.entities.NewsSourceEntity

data class NewsSourceModel(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String,
    val isUsed: Boolean = false
) {
    fun toEntity():NewsSourceEntity = NewsSourceEntity(id, name, description, url, category, language, country , isUsed)

    companion object {
        fun fromEntity(newsSourceEntity: NewsSourceEntity): NewsSourceModel = NewsSourceModel(
            newsSourceEntity.id,
            newsSourceEntity.name,
            newsSourceEntity.description,
            newsSourceEntity.url,
            newsSourceEntity.category,
            newsSourceEntity.language,
            newsSourceEntity.country,
            newsSourceEntity.isUsed
        )
    }
}

fun List<NewsSourceModel>.toEntity(): List<NewsSourceEntity> = map {it.toEntity() }
fun List<NewsSourceEntity>.toModel(): List<NewsSourceModel> = map { NewsSourceModel.fromEntity(it) }
