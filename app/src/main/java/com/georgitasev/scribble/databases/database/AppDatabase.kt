package com.georgitasev.scribble.databases.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.georgitasev.scribble.databases.daos.NoteDao
import com.georgitasev.scribble.databases.entities.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}