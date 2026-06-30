package com.ida.notesodd.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ida.notesodd.data.Deleted
import com.ida.notesodd.data.Note

@Dao
interface DeletedDao {
    @Insert
    fun addDeletedNote(deleted: Deleted)

    @Query("SELECT * FROM deleted_notes")
    fun getAllDeletedNotes(): List<Deleted>

    @Delete
    fun deleteDeletedNote(deleted: Deleted)
}