package com.georgitasev.scribble.ui.views.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAlertDialog(
    dialogTitle: String,
    dialogContent: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    AlertDialog(
        icon = {
            Icon(
                Icons.Default.Warning,
                contentDescription = "Warning icon for alert dialog",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        },
        containerColor = colors.primaryContainer,
        title = {
            Text(
                text = dialogTitle,
                color = colors.onPrimaryContainer
            )
        },
        text = {
            Text(
                text = dialogContent,
                color = colors.onPrimaryContainer
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    "Confirm",
                    color = colors.onPrimaryContainer
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(
                    "Dismiss",
                    color = colors.onPrimaryContainer
                )
            }
        }
    )
}