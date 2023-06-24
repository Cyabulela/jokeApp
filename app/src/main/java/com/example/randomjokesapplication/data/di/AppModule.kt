package com.example.randomjokesapplication.data.di

import com.example.randomjokesapplication.data.remote.JokesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun jokesApiProvider(): JokesApi {
        return Retrofit.Builder()
            .baseUrl(JokesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokesApi::class.java)
    }
}
