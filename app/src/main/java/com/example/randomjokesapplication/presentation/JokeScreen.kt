package com.example.randomjokesapplication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomjokesapplication.R
import com.example.randomjokesapplication.domain.model.Joke

@Composable
fun JokeScreen(
    state: JokesViewModel.State,
    share: (Joke) -> Unit,
    onNextClick: () -> Unit,
    onDropDownSelect: (Int) -> Unit,
    dropDownSelectedIndex: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Joke",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            val menus = JokesType.values().map {
                it.jokeType
            }

            Spinner(
                menus = menus,
                selectedIndex = dropDownSelectedIndex,
                onItemSelect = onDropDownSelect,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.joke.setup,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = state.joke.punchline,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    onClick = {
                        share(state.joke)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_share_24),
                        contentDescription = "Share joke"
                    )
                }

                IconButton(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    onClick = onNextClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_next_24),
                        contentDescription = "Next joke"
                    )
                }
            }
        }
    }
}

@Preview(name = "JokeScreen")
@Composable
private fun PreviewJokeScreen() {
    JokeScreen(
        JokesViewModel.State(joke = Joke("Cool", "setup", "style")),
        {},
        {},
        {},
        0,
        modifier = Modifier.fillMaxSize()
    )
}
