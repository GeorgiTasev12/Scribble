package com.georgitasev.scribble.databases.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.georgitasev.scribble.databases.entities.Note
import com.georgitasev.scribble.models.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)
    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun read(): Flow<List<Note>>
    @Update
    suspend fun update(note: Note)
    @Query("DELETE FROM ${Constants.TABLE_NAME} WHERE id = :id")
    suspend fun deleteNoteById(id: Int)
    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE id = :id LIMIT 1")
    suspend fun getNoteById(id: Int): Note?
}