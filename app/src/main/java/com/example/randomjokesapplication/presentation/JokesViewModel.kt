package com.example.randomjokesapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomjokesapplication.domain.model.Joke
import com.example.randomjokesapplication.domain.use_case.JokesUseCaseFactory
import com.example.randomjokesapplication.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val useCase: JokesUseCaseFactory
) : ViewModel() {
	
    var state by mutableStateOf(State())
        private set
	
    var selectedDropDownMenuIndex by mutableIntStateOf(0)
        private set
	
    var dropDownMenuExpanded by mutableStateOf(false)
        private set
	
    init {
        getRandomJoke()
    }
	
    fun nextJoke() {
        state = State()
        val type = JokesType.values().toList()
        getRandomJoke(type[this.selectedDropDownMenuIndex])
    }
	
    private fun getRandomJoke(type: JokesType = JokesType.RANDOM) {
        viewModelScope.launch {
            val flow = if (type == JokesType.RANDOM) {
                useCase.getJokeUseCase()
            } else {
                useCase.getRandomJokeByTypeUseCase(type.jokeType)
            }
			
            flow.collectLatest { result ->
                when (result) {
                    is Resources.Error -> {
                        state = state.copy(
                            error = ErrorHandler(
                                isErrorVisible = true,
                                errorMessage = result.message!!
                            )
                        )
                    }
					
                    is Resources.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
					
                    is Resources.Success -> {
                        state = state.copy(joke = result.data!!)
                    }
                }
            }
        }
    }
	
    fun onDropDownMenuSelect(index: Int) {
        selectedDropDownMenuIndex = index
        nextJoke()
    }
	
    fun errorDismissal(value: Boolean) {
        state = state.copy(error = ErrorHandler(isErrorVisible = value, errorMessage = ""))
        nextJoke()
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
	
    fun onDropDownMenuExpandedToggle(expanded: Boolean) {
        dropDownMenuExpanded = expanded
    }
	
    data class State(
        val joke: Joke = Joke(
            punchline = "",
            setup = "",
            type = ""
        ),
        val error: ErrorHandler = ErrorHandler(),
        val isLoading: Boolean = false,
        val isDropDownVisible: Boolean = false
    )
}
