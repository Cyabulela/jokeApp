package com.example.randomjokesapplication.domain.use_case

import com.example.randomjokesapplication.domain.model.Joke
import com.example.randomjokesapplication.domain.repository.JokeRepository
import com.example.randomjokesapplication.util.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRandomJokeByTypeUseCase(
    private val repository: JokeRepository
) {

    suspend operator fun invoke(type : String) : Flow<Resources<Joke>> {
        return flow {
            emit(Resources.Loading(true))
            withHandledException {
                val result = repository.getRandomJokeByType(type)
                emit(Resources.Success(result))
            }
            emit(Resources.Loading(false))
        }
    }
}