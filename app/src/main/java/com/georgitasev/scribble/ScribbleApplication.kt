package com.georgitasev.scribble

import android.app.Application
import androidx.room.Room
import com.georgitasev.scribble.databases.database.AppDatabase
import com.georgitasev.scribble.models.Constants
import com.georgitasev.scribble.repositories.NoteRepository

class ScribbleApplication: Application() {
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    val repository by lazy {
        NoteRepository(appDB = db)
    }
}