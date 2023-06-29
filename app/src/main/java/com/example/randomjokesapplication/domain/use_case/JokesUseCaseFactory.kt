package com.example.randomjokesapplication.domain.use_case // ktlint-disable package-name

import com.example.randomjokesapplication.domain.repository.JokeRepository
import javax.inject.Inject

class JokesUseCaseFactory @Inject constructor(
    repository: JokeRepository
) {
    val getJokeUseCase = GetRandomJokeUseCase(repository)
    val getRandomJokeByTypeUseCase = GetRandomJokeByTypeUseCase(repository)
}
