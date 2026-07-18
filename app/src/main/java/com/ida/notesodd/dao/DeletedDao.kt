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

    @Query("DELETE FROM deleted_notes" +
            " WHERE deletedTime <= :expiredDate")
    suspend fun deletePermanently(expiredDate: Long)

    /*30 - iyun 18:43   8000

    30 - iyun 18:48  8000 + 5 * 60 * 1000  */
}