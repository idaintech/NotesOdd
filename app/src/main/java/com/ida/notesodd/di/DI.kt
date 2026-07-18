package com.ida.notesodd.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ida.notesodd.dao.NoteDb
import com.ida.notesodd.vm.DeletedVM
import com.ida.notesodd.vm.NoteVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

//KOIN, DI - Dependency Injection

val appModule = module {
    val MIGRATION_1_2 = object: Migration(1, 2){
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                """
                    CREATE TABLE deleted_notes(
                        title STRING NOT NULL DEFAULT '',
                        description STRING NOT NULL DEFAULT '',
                        createdTime INTEGER NOT NULL DEFAULT 0,
                        id INTEGER NOT NULL PRIMARY KEY DEFAULT 0,
                        deletedTime INTEGER NOT NULL DEFAULT 0
                    )
                """.trimIndent()
            )
        }
    }
    single {
        //server
        //firebase
        Room.databaseBuilder(
            get(), //kerek zatti ozin tawip al
            NoteDb::class.java,
            "my_notes_db"
        ).allowMainThreadQueries().addMigrations(MIGRATION_1_2)
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

    viewModel {
        DeletedVM(get())
    }

}
