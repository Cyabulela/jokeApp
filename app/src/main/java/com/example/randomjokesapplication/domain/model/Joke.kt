package com.example.randomjokesapplication.domain.model

data class Joke(
    val punchline: String,
    val setup: String,
    val type: String
) {
    override fun toString(): String {
        return """
            $setup
            
            $punchline
        """.trimIndent()
    }
}
