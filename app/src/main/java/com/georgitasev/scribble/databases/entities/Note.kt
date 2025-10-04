package com.georgitasev.scribble.databases.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.georgitasev.scribble.models.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo("description") val description: String
)
