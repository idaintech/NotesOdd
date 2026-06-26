package com.ida.notesodd.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ida.notesodd.data.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDb: RoomDatabase() {
    abstract fun getDao(): NoteDao
}