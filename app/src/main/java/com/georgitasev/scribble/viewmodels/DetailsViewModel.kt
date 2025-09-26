package com.georgitasev.scribble.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsViewModel : ViewModel() {
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    fun onTitleChange(newValue: String) {
        _title.value = newValue
    }

    fun onDescriptionChange(newValue: String) {
        _description.value = newValue
    }
}