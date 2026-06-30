package com.ida.notesodd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deleted_notes")
data class Deleted(
    val note: Note,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0, //1 2 3
    val deletedTime: Long =
        System.currentTimeMillis() //27.06.2026 18:51
)
//"dhdhdgrr63847913r7tq9"
