package com.georgitasev.scribble.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.georgitasev.scribble.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreScreen(
    body: @Composable (PaddingValues) -> Unit,
    hasFAB: Boolean = false,
    hasTopBar: Boolean = false,
    appBarTitle: String = "",
    onPopClick: () -> Unit = {},
    onFABClick: () -> Unit = {},
    onSaveNoteClick: () -> Unit = {},
    onSaveFileClick: () -> Unit = {},
    isFieldEmpty: Boolean = false
) {
    val colors = MaterialTheme.colorScheme

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colors.inversePrimary,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
                        IconButton(
                            onClick = onSaveFileClick,
                        ) {
                            Icon(
                                painterResource(R.drawable.save_file),
                                contentDescription = "Save note",
                                tint = colors.onPrimaryContainer,
                                modifier = Modifier.size(
                                    width = 25.dp,
                                    height = 25.dp
                                )
                            )
                        }
                        IconButton(
                            onClick = {
                                if (isFieldEmpty) {
                                    scope.launch {
                                        snackbarHostState
                                            .showSnackbar(
                                                message = "Your both title and description are empty, please fill them with content.",
                                                duration = SnackbarDuration.Short
                                            )
                                    }
                                } else {
                                    onSaveNoteClick()
                                }
                            },
                        ) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Save",
                                tint = colors.onPrimaryContainer
                            )
                        }
                    }
                )
            } else null
        },
        floatingActionButton = {
            if (hasFAB) {
                FloatingActionButton(
                    containerColor = colors.primaryContainer,
                    onClick = onFABClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.note_add),
                        contentDescription = "Add a note.",
                        tint = colors.onPrimaryContainer,
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