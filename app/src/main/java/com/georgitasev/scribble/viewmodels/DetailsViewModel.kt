package com.georgitasev.scribble.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgitasev.scribble.databases.entities.Note
import com.georgitasev.scribble.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repo: NoteRepository) : ViewModel() {
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    fun createNote(
        title: String,
        description: String
    ) {
        viewModelScope.launch {
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
        viewModelScope.launch {
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
        viewModelScope.launch {
            val note = repo.getNoteById(id)
            note?.let { note ->
                _title.value = note.title
                _description.value = note.description
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