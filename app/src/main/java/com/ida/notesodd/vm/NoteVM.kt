package com.ida.notesodd.vm

import androidx.lifecycle.ViewModel
import com.ida.notesodd.dao.DeletedDao
import com.ida.notesodd.dao.NoteDao
import com.ida.notesodd.data.Deleted
import com.ida.notesodd.data.Note
import org.koin.core.Koin

class NoteVM(private val dao: NoteDao,
             private val deletedDao: DeletedDao
): ViewModel(){

    fun getAllNotes(): List<Note>{
        return dao.getAllNotes()
    }

    fun add(note: Note){
        dao.addNote(note)
    }

    fun deleteNote(note: Note){
        deletedDao.addDeletedNote(Deleted(note))
        dao.deleteNote(note)
    }
}