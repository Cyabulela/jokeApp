package com.example.randomjokesapplication.domain.repository

import com.example.randomjokesapplication.domain.model.Joke

interface JokeRepository {

    suspend fun getRandomJoke() : Joke

    suspend fun getRandomJokes() : List<Joke>

    suspend fun getJokeByType(type : String) : Joke

    suspend fun getRandomJokesByType(type : String) : List<Joke>
}