package com.georgitasev.scribble.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgitasev.scribble.databases.entities.Note
import com.georgitasev.scribble.repositories.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repo: NoteRepository): ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            repo.readNotes().collect { list ->
                _notes.value = list
                _isLoading.value = false
            }
        }
    }

    fun removeNote(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _isLoading.value = true
        repo.removeNote(id = id)
        _isLoading.value = false
    }
}