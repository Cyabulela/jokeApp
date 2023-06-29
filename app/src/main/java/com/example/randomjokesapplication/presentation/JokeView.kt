package com.example.randomjokesapplication.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.randomjokesapplication.R.drawable
import com.example.randomjokesapplication.domain.model.Joke
import com.example.randomjokesapplication.presentation.JokesViewModel.State

@Composable
fun JokeView(
    dropDownSelectedIndex: Int,
    onDropDownSelect: (Int) -> Unit,
    onToggleDropDownMenu: (Boolean) -> Unit,
    dropDownMenuExpanded: Boolean,
    state: State,
    share: (Joke) -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
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
            onDropDownToggleChange = onToggleDropDownMenu,
            expanded = dropDownMenuExpanded,
            modifier = Modifier.align(Alignment.End)
        )
		
        Spacer(modifier = Modifier.height(20.dp))
		
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
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
			
            Text(
                text = state.joke.type,
                style = MaterialTheme.typography.bodySmall
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
                    painter = painterResource(id = drawable.baseline_share_24),
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
                    painter = painterResource(id = drawable.baseline_skip_next_24),
                    contentDescription = "Next joke"
                )
            }
        }
    }
}
