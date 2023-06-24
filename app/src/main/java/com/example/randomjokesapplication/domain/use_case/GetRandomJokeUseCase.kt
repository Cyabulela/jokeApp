package com.example.randomjokesapplication.domain.use_case // ktlint-disable package-name

import com.example.randomjokesapplication.domain.model.Joke
import com.example.randomjokesapplication.domain.repository.JokeRepository
import com.example.randomjokesapplication.util.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRandomJokeUseCase(
    private val jokeRepository: JokeRepository
) {

    suspend operator fun invoke(): Flow<Resources<Joke>> {
        return flow {
            emit(Resources.Loading(true))
            withHandledException {
                val result = jokeRepository.getRandomJoke()
                emit(Resources.Success(result))
            }
            emit(Resources.Loading(false))
        }
    }
}
