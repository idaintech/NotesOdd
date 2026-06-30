package com.ida.notesodd.di

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.ida.notesodd.dao.NoteDb
import com.ida.notesodd.vm.NoteVM
import org.koin.core.context.GlobalContext.get
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

//KOIN, DI - Dependency Injection

val appModule = module {
    single {
        //server
        //firebase
        Room.databaseBuilder(
            get(), //kerek zatti ozin tawip al
            NoteDb::class.java,
            "my_notes_db"
        ).allowMainThreadQueries()
            .build()
    }
    single {
        get<NoteDb>().getDao()
    }
    single {
        get<NoteDb>().getDeletedDao()
    }
}
val viewModelModule = module {
    viewModel {
        NoteVM(get(), get())
    }
    //DeletedVM
}