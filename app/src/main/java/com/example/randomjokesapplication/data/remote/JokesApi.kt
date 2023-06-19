package com.example.randomjokesapplication.data.remote

import com.example.randomjokesapplication.data.dto.JokeDto
import retrofit2.http.GET

interface JokesApi {

    @GET("random_joke")
    suspend fun getRandomJoke() : JokeDto

    @GET("random_ten")
    suspend fun getRandomJokes() : List<JokeDto>

    @GET("jokes/:type/random")
    suspend fun getJokeByType(type : String) : JokeDto

    @GET("jokes/:type/ten")
    suspend fun getRandomJokesByType(type : String) : List<JokeDto>

    @GET("jokes/:id")
    suspend fun getJokeById(id : Int) : JokeDto

    companion object {
        const val BASE_URL = "https://official-joke-api.appspot.com/"
    }
}