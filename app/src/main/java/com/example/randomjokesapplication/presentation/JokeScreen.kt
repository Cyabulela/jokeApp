package com.example.randomjokesapplication.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomjokesapplication.domain.model.Joke
import com.example.randomjokesapplication.presentation.JokesViewModel.State

/**
 * Main Screen of the app
 */
@Composable
fun JokeScreen(
    modifier: Modifier = Modifier,
    state: State,
    share: (Joke) -> Unit,
    onNextClick: () -> Unit,
    onDropDownSelect: (Int) -> Unit,
    onToggleDropDownMenu: (Boolean) -> Unit,
    errorVisibility: (Boolean) -> Unit,
    dropDownSelectedIndex: Int = 0,
    dropDownMenuExpanded: Boolean = false
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        JokeView(
            dropDownSelectedIndex,
            onDropDownSelect,
            onToggleDropDownMenu,
            dropDownMenuExpanded,
            state,
            share,
            onNextClick
        )
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.Green,
                strokeWidth = 2.dp
            )
        }
		
        if (state.error.isErrorVisible) {
            ErrorAlertDialog(
                error = state.error,
                errorVisibility = errorVisibility
            )
        }
    }
}

@Preview(name = "JokeScreen")
@Composable
private fun PreviewJokeScreen() {
    JokeScreen(
        modifier = Modifier.fillMaxSize(),
        state = State(
            joke = Joke(
                punchline = "The karma invents trust which is not hermetic.",
                setup = "Never ransack a turner.",
                type = "random"
            )
        ),
        {},
        {},
        {},
        {},
        {}
    )
}
