package com.ida.notesodd.vm

import androidx.lifecycle.ViewModel
import com.ida.notesodd.dao.NoteDao
import com.ida.notesodd.data.Note

class NoteVM(private val dao: NoteDao): ViewModel(){

    fun getAllNotes(): List<Note>{
        return dao.getAllNotes()
    }

    fun add(note: Note){
        dao.addNote(note)
    }
}