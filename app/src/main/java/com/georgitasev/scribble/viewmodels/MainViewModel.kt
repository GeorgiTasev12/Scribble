package com.georgitasev.scribble.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgitasev.scribble.databases.entities.Note
import com.georgitasev.scribble.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repo: NoteRepository): ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    init {
        viewModelScope.launch {
            repo.readNotes().collect { list ->
                _notes.value = list
            }
        }
    }

    fun removeNote(id: Int) = viewModelScope.launch {
        repo.removeNote(id = id)
    }
}