package com.anilyilmaz.awesomesunsetwallpapers.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  NetworkModule {
    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder().addHeader("Authorization",
                "AYcBbQE9tWR1DQDCy4Lh3XobkrOi9XNM1J4v3x4RFspWNuGOZq6Aqh9u").build()
        chain.proceed(request)})
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                },
        )
        .build()
}