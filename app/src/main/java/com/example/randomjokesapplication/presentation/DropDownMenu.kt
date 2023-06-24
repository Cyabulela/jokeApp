package com.example.randomjokesapplication.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun Spinner(
    menus: List<String>,
    selectedIndex: Int,
    onItemSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier.wrapContentSize()) {
        Text(
            text = menus[selectedIndex],
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.clickable(onClick = { expanded = true })
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            menus.forEachIndexed { index, item ->
                DropdownMenuItem(text = {
                    Text(text = item)
                }, onClick = {
                    onItemSelect(index)
                    expanded = false
                })
            }
        }
    }
}
