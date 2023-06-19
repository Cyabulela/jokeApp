package com.example.randomjokesapplication.data.reposotiory

import com.example.randomjokesapplication.data.mapper.toJoke
import com.example.randomjokesapplication.data.remote.JokesApi
import com.example.randomjokesapplication.domain.model.Joke
import com.example.randomjokesapplication.domain.repository.JokeRepository
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val joke : JokesApi
) : JokeRepository {

    override suspend fun getRandomJoke(): Joke {
        return joke.getRandomJoke().toJoke()
    }

    override suspend fun getRandomJokes(): List<Joke> {
        return joke.getRandomJokes().map { it.toJoke() }
    }

    override suspend fun getJokeByType(type: String): Joke {
        return joke.getJokeByType(type).toJoke()
    }

    override suspend fun getRandomJokesByType(type: String): List<Joke> {
        return joke.getRandomJokesByType(type).map { it.toJoke() }
    }
}