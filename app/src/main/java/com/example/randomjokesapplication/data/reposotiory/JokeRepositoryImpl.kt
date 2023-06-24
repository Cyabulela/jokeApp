package com.example.randomjokesapplication.data.reposotiory

import com.example.randomjokesapplication.data.mapper.toJoke
import com.example.randomjokesapplication.data.remote.JokesApi
import com.example.randomjokesapplication.domain.model.Joke
import com.example.randomjokesapplication.domain.repository.JokeRepository
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val joke: JokesApi
) : JokeRepository {

    override suspend fun getRandomJoke(): Joke {
        return joke.getRandomJoke().body()?.toJoke() ?: throw Exception("Result type don't match")
    }

    override suspend fun getRandomJokeByType(type: String): Joke {
        return joke.getJokeByType(type).body()?.toJoke()
            ?: throw Exception("Result type don't match")
    }
}
