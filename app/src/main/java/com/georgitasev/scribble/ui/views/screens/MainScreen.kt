package com.georgitasev.scribble.ui.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.georgitasev.scribble.models.Routes
import com.georgitasev.scribble.ui.views.CoreScreen
import com.georgitasev.scribble.ui.views.composables.ListTileItem
import com.georgitasev.scribble.viewmodels.MainViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.georgitasev.scribble.ui.views.composables.DeleteAlertDialog

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController
) {
    val notesList = viewModel.notes.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    val openDialog = remember { mutableStateOf(false) }
    val noteToDelete = remember { mutableStateOf<Int?>(null) }

    val color = MaterialTheme.colorScheme

    CoreScreen(
        hasFAB = true,
        onFABClick = {
            navController.navigate(Routes.DETAILS_SCREEN.name)
        },
        body = { innerPadding ->
            when {
                isLoading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        CircularProgressIndicator(
                            color = color.inversePrimary
                        )
                    }
                }
                notesList.isEmpty() -> {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Notes are empty, please add some notes",
                            color = color.onPrimary
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(
                                start = 12.dp,
                                end = 12.dp,
                                top = 40.dp
                            )
                    ) {
                        items(count = notesList.size) { index ->
                            val note = notesList[index]

                            ListTileItem(
                                title = note.title,
                                description = note.description,
                                onEditClick = {
                                    navController.navigate("${Routes.DETAILS_SCREEN.name}/${note.id}")
                                },
                                onDeleteButtonClick = {
                                    noteToDelete.value = note.id
                                    openDialog.value = true
                                }
                            )
                        }
                    }

                    if (openDialog.value && noteToDelete.value != null) {
                        DeleteAlertDialog(
                            dialogTitle = "Warning",
                            dialogContent = "You're about to remove the note from your list. Once removed, it cannot be restored. Are you sure you want to proceed?",
                            onConfirmation = {
                                viewModel.removeNote(id = noteToDelete.value!!)
                                openDialog.value = false
                                noteToDelete.value = null
                            },
                            onDismissRequest = {
                                openDialog.value = false
                                noteToDelete.value = null
                            }
                        )
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(navController = rememberNavController())
}