package com.ida.notesodd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    val title: String,
    val description: String,
    val createdTime: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
