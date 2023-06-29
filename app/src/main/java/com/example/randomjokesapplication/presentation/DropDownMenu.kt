package com.example.randomjokesapplication.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Spinner(
    menus: List<String>,
    selectedIndex: Int,
    onItemSelect: (Int) -> Unit,
    expanded: Boolean = false,
    onDropDownToggleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.wrapContentSize()) {
        Text(
            text = menus[selectedIndex],
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.clickable(onClick = { onDropDownToggleChange(true) })
        )
		
        DropdownMenu(expanded = expanded, onDismissRequest = { onDropDownToggleChange(false) }) {
            menus.forEachIndexed { index, item ->
                DropdownMenuItem(text = {
                    Text(text = item)
                }, onClick = {
                    onItemSelect(index)
                    onDropDownToggleChange(false)
                })
            }
        }
    }
}
