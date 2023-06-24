package com.example.randomjokesapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomjokesapplication.domain.model.Joke
import com.example.randomjokesapplication.domain.use_case.JokesUseCaseFactory
import com.example.randomjokesapplication.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val useCase: JokesUseCaseFactory
) : ViewModel() {

    init {
        getRandomJoke()
    }

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    var selectedDropDownMenuIndex by mutableIntStateOf(0)
        private set

    fun nextJoke() {
        _state.update {
            State()
        }
        val type = JokesType.values().toList()
        getRandomJoke(type[this.selectedDropDownMenuIndex])
    }

    private fun getRandomJoke(type: JokesType = JokesType.RANDOM) {
        viewModelScope.launch {
            val result = if (type == JokesType.RANDOM) {
                useCase.getJokeUseCase()
            } else {
                useCase.getRandomJokeByTypeUseCase(type.jokeType)
            }

            result.collectLatest {
                when (it) {
                    is Resources.Error -> {
                        _state.update { state ->
                            state.copy(
                                error = ErrorHandler(
                                    isErrorVisible = true,
                                    errorMessage = it.message!!
                                )
                            )
                        }
                    }

                    is Resources.Loading -> {
                        _state.update { state ->
                            state.copy(isLoading = it.isLoading)
                        }
                    }

                    is Resources.Success -> {
                        _state.update { state ->
                            state.copy(joke = it.data!!)
                        }
                    }
                }
            }
        }
    }

    fun onDropDownMenuSelect(index: Int) {
        selectedDropDownMenuIndex = index
    }

    fun shareJoke(joke: Joke, context: Context) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, joke.toString())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intent, null)
        context.startActivity(shareIntent)
    }

    data class State(
        val joke: Joke = Joke(
            punchline = "",
            setup = "",
            type = ""
        ),
        val error: ErrorHandler = ErrorHandler(
            isErrorVisible = false,
            errorMessage = ""
        ),
        val isLoading: Boolean = false
    )
}
