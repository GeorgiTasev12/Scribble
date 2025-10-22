package com.georgitasev.scribble.ui.views.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.georgitasev.scribble.ui.views.CoreScreen
import com.georgitasev.scribble.viewmodels.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = viewModel(),
    navController: NavHostController,
    noteId: Int? = null
) {
    val colors = MaterialTheme.colorScheme
    val context = LocalContext.current

    val title by viewModel.title.collectAsStateWithLifecycle()
    val description by viewModel.description.collectAsStateWithLifecycle()

    val createDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/plain")
    ) { uri: Uri? ->
        uri?.let {
            viewModel.saveFileToUri(
                context = context,
                uri = it,
                content = description
            )
        }
    }

    LaunchedEffect(noteId) {
        noteId?.let { viewModel.loadNoteById(id = it) }
    }

    CoreScreen(
        hasTopBar = true,
        appBarTitle = "Details Screen",
        onPopClick = { navController.popBackStack() },
        onSaveNoteClick = {
            noteId?.let { id ->
                viewModel.updateNote(
                    id = id,
                    title = title.ifEmpty { description },
                    description = if (title.isEmpty()) "" else description
                )
            } ?: run {
                viewModel.createNote(
                    title = title.ifEmpty { description },
                    description = if (title.isEmpty()) "" else description
                )
            }
            navController.popBackStack()
        },
        onSaveFileClick = {
            val suggestedName = "${title.ifBlank { "Untitled" }}.txt"
            createDocumentLauncher.launch(suggestedName)
        },
        body = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    TextField(
                        value = title,
                        onValueChange = { viewModel.onTitleChange(it) },
                        singleLine = true,
                        shape = RectangleShape,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = colors.primaryContainer,
                            focusedContainerColor = colors.primaryContainer,
                            cursorColor = colors.onPrimary
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        textStyle = TextStyle(
                            color = colors.onPrimaryContainer,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif
                        ),
                        placeholder = {
                            Text(
                                "Enter title here..",
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = description,
                        onValueChange = { viewModel.onDescriptionChange(it) },
                        textStyle = TextStyle(
                            color = colors.onPrimary,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        placeholder = {
                            Text(
                                "Enter description here..",
                                color = colors.onPrimary,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            cursorColor = colors.onPrimaryContainer
                        ),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreen() {
    DetailsScreen(
        navController = rememberNavController(),
        noteId = 0
    )
}