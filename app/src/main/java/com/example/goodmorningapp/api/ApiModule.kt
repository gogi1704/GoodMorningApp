package com.example.goodmorningapp.api

import com.example.goodmorningapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    companion object {
        const val WEATHER_BASE_URL = "https://api.weatherapi.com/v1/"
    }

    @Singleton
    @Provides
    fun providesLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(logging:HttpLoggingInterceptor):OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30 , TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(WEATHER_BASE_URL)
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): ApiService = retrofit.create()
}