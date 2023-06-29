package com.example.randomjokesapplication.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ErrorAlertDialog(
    error: ErrorHandler,
    errorVisibility: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(20.dp)
    ) {
        Dialog(onDismissRequest = { errorVisibility(false) }) {
            Text(
                text = error.errorMessage,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(name = "ErrorAlertDialog")
@Composable
private fun PreviewErrorAlertDialog() {
    ErrorAlertDialog(
        error = ErrorHandler(true, "Message"),
        errorVisibility = {}
    )
}
