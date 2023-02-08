package com.example.goodmorningapp.repository.di

import com.example.goodmorningapp.repository.NoteRepository
import com.example.goodmorningapp.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsNoteRepository(impl: NoteRepositoryImpl): NoteRepository


}