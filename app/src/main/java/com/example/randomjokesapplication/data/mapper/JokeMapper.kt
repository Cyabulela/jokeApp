package com.example.randomjokesapplication.data.mapper

import com.example.randomjokesapplication.data.dto.JokeDto
import com.example.randomjokesapplication.domain.model.Joke

fun JokeDto.toJoke() : Joke {
    return Joke(
        type = type,
        setup = setup,
        punchline = punchline
    )
}