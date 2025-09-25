package com.georgitasev.scribble.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.georgitasev.scribble.R

@Composable
fun CoreScreen(
    body: @Composable (PaddingValues) -> Unit,
    hasFAB: Boolean = false,
    onFABClick: Function0<Unit> = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        floatingActionButton = {
            if (hasFAB) {
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    onClick = onFABClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.note_add),
                        contentDescription = "Add a note.",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(size = 30.dp)
                    )
                }
            } else null
        },
        content = { innerPadding ->
            body(innerPadding)
        }
    )
}