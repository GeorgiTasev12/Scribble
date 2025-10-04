package com.georgitasev.scribble.repositories

import com.georgitasev.scribble.databases.database.AppDatabase
import com.georgitasev.scribble.databases.entities.Note

class NoteRepository(val appDB: AppDatabase) {
    private val database = appDB.noteDao()
    
    suspend fun createNote(note: Note) = database.insert(note)
    fun readNotes() = database.read()
    suspend fun updateNote(note: Note) = database.update(note)
    suspend fun removeNote(id: Int) = database.deleteNoteById(id = id)
    suspend fun getNoteById(id: Int): Note? = database.getNoteById(id = id)
}