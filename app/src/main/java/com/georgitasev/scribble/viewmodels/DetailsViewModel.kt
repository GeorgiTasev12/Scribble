package com.georgitasev.scribble.viewmodels

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgitasev.scribble.databases.entities.Note
import com.georgitasev.scribble.repositories.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class DetailsViewModel(private val repo: NoteRepository) : ViewModel() {
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    fun createNote(
        title: String,
        description: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createNote(
                Note(
                    title = title,
                    description = description
                )
            )
        }
    }

    fun updateNote(
        id: Int,
        title: String,
        description: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateNote(
                Note(
                    id = id,
                    title = title,
                    description = description
                )
            )
        }
    }

    fun loadNoteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = repo.getNoteById(id)
            note?.let { note ->
                _title.value = note.title
                _description.value = note.description
            }
        }
    }

    fun saveFileToUri(context: Context, uri: Uri, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(content.toByteArray())
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "File saved successfully!", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error saving file: ${e.message}", Toast.LENGTH_LONG).show()
                }
                throw Exception(e)
            }
        }
    }

    fun onTitleChange(newValue: String) {
        _title.value = newValue
    }

    fun onDescriptionChange(newValue: String) {
        _description.value = newValue
    }
}