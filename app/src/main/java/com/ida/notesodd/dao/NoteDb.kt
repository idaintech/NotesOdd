package com.ida.notesodd.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ida.notesodd.data.Deleted
import com.ida.notesodd.data.Note

@Database(
    entities = [Note::class, Deleted::class],
    version = 2
)
abstract class NoteDb: RoomDatabase() {
    abstract fun getDao(): NoteDao
    abstract fun getDeletedDao(): DeletedDao
}