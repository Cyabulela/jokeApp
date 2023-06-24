package com.example.randomjokesapplication.data.remote

import com.example.randomjokesapplication.data.dto.JokeDto
import retrofit2.http.GET

interface JokesApi {

    @GET("random_joke")
    suspend fun getRandomJoke(): JokeDto

    @GET("jokes/:type/random")
    suspend fun getJokeByType(type: String): JokeDto

    companion object {
        const val BASE_URL = "https://official-joke-api.appspot.com/"
    }
}
