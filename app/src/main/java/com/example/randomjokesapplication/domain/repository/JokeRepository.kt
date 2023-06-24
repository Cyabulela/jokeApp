package com.example.randomjokesapplication.domain.repository

import com.example.randomjokesapplication.domain.model.Joke
import java.io.IOException
import retrofit2.HttpException

interface JokeRepository {

    @Throws(HttpException::class, IOException::class, Exception::class)
    suspend fun getRandomJoke(): Joke

    @Throws(HttpException::class, IOException::class, Exception::class)
    suspend fun getRandomJokeByType(type: String): Joke
}
