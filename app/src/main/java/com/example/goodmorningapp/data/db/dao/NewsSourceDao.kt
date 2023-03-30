package com.example.goodmorningapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.goodmorningapp.data.db.entities.NewsSourceEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsSourceDao {

    @Query("SELECT * FROM NewsSourceEntity")
    fun getAll(): Flow<List<NewsSourceEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(newsEntity: List<NewsSourceEntity>)

    @Query("UPDATE NewsSourceEntity SET isUsed = CASE WHEN isUsed THEN 0 ELSE 1 END WHERE id = :id")
    suspend fun check(id: String)
}