package com.example.randomjokesapplication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomjokesapplication.domain.model.Joke

@Composable
fun JokeScreen(
    state: JokesViewModel.State,
    share: (Joke) -> Unit,
    onNextClick: () -> Unit,
    onDropDownSelect : (Int) -> Unit,
    dropDownSelectedIndex: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Joke",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        val menus = JokesType.values().map {
            it.jokeType
        }

        Spinner(
            menus = menus,
            selectedIndex = dropDownSelectedIndex,
            onItemSelect = onDropDownSelect,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Preview(name = "JokeScreen")
@Composable
private fun PreviewJokeScreen() {
    JokeScreen(
        JokesViewModel.State(),
        {},
        {},
        {},
        0,
        modifier = Modifier.fillMaxSize()
    )
}
