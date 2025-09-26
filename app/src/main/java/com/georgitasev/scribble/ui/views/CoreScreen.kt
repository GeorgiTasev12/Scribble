package com.georgitasev.scribble.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.georgitasev.scribble.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreScreen(
    body: @Composable (PaddingValues) -> Unit,
    hasFAB: Boolean = false,
    hasTopBar: Boolean = false,
    appBarTitle: String = "",
    isTitleEmpty: Boolean = false,
    isDescriptionEmpty: Boolean = false,
    onPopClick: () -> Unit = {},
    onFABClick: () -> Unit = {},
    onSaveNoteClick: () -> Unit = {},
) {
    val colors = MaterialTheme.colorScheme

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        topBar = {
            if (hasTopBar) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colors.primaryContainer,
                        titleContentColor = colors.onPrimaryContainer,
                    ),
                    title = {
                        Text(
                            appBarTitle,
                        )
                    },
                    navigationIcon = {
                        if (hasTopBar) {
                            IconButton(onClick = onPopClick) {
                                Icon(
                                    Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = colors.onPrimaryContainer
                                )
                            }
                        } else null
                    },
                    actions = {
                        IconButton(onClick = onSaveNoteClick) {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = "Save",
                                tint = if (isTitleEmpty && isDescriptionEmpty)
                                    colors.onPrimaryContainer.copy(alpha = 20f)
                                else
                                    colors.onPrimaryContainer
                            )
                        }
                    }
                )
            } else null
        },
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