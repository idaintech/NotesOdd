package com.ida.notesodd.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ida.notesodd.dao.DeletedDao
import com.ida.notesodd.data.Deleted
import com.ida.notesodd.setting.Setting.Companion.EXPIRED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeletedVM(private val deletedDao: DeletedDao)
    : ViewModel() {
    fun deletePermanently(){
        viewModelScope.launch(Dispatchers.IO) {
            deletedDao.deletePermanently(
                System.currentTimeMillis() - EXPIRED
            )
        }
    }

    fun getAllDeletedNotes(): List<Deleted>{
        return deletedDao.getAllDeletedNotes()
    }
}