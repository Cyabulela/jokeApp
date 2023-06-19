package com.example.randomjokesapplication.domain.use_case

import com.example.randomjokesapplication.domain.repository.JokeRepository
import javax.inject.Inject

sealed class JokesUseCaseFactory @Inject constructor(
    private val repository: JokeRepository
) {

}