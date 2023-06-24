package com.example.randomjokesapplication.data.di

import com.example.randomjokesapplication.data.reposotiory.JokeRepositoryImpl
import com.example.randomjokesapplication.domain.repository.JokeRepository
import com.example.randomjokesapplication.domain.use_case.JokesUseCaseFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class JokesBindingModules {

    @Binds
    @Singleton
    abstract fun bindJokesRepository(jokeRepositoryImpl: JokeRepositoryImpl): JokeRepository

    @Binds
    @Singleton
    abstract fun bindJokesUseCases(useCases: JokesUseCaseFactory): JokesUseCaseFactory
}
