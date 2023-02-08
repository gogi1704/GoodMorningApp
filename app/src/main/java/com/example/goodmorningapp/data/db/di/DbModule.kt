package com.example.goodmorningapp.data.db.di

import android.content.Context
import androidx.room.Room
import com.example.goodmorningapp.data.db.AppDb
import com.example.goodmorningapp.data.db.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    companion object {
        const val DATA_BASE_NAME = "APP_DATA_BASE"
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDb =
        Room.databaseBuilder(context, AppDb::class.java, DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesNoteDao(appDb: AppDb): NoteDao = appDb.noteDao()
}