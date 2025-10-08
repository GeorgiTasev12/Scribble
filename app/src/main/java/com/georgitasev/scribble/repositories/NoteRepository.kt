package com.georgitasev.scribble.repositories

import com.georgitasev.scribble.databases.database.AppDatabase
import com.georgitasev.scribble.databases.entities.Note

class NoteRepository(val appDB: AppDatabase) {
    private val database = appDB.noteDao()
    
    suspend fun createNote(note: Note) {
        try {
            database.insert(note)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    fun readNotes() = database.read()

    suspend fun updateNote(note: Note) {
        try {
            database.update(note)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun removeNote(id: Int) {
        try {
            database.deleteNoteById(id = id)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getNoteById(id: Int): Note? {
        try {
            return database.getNoteById(id = id)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}