package com.ida.notesodd

import android.text.TextWatcher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ida.notesodd.data.Note
import com.ida.notesodd.vm.NoteVM


@Composable
fun NotesUI(vm: NoteVM) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    var deleteShowDialog by remember {
        mutableStateOf(false)
    }
    var notes by remember {
        mutableStateOf(vm.getAllNotes())
    }
    notes = vm.getAllNotes()

    var deleteNote by remember {
        mutableStateOf(
        Note("", "")
        )
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
            }) {
                Icon(Icons.Default.Add,
                    "add icon")
            }
        }
    ) { ip ->
        LazyVerticalGrid (

            modifier = Modifier
                .fillMaxSize().padding(ip),
            columns = GridCells.Fixed(2)
        ) {
            items(notes) { note ->
                ItemUI(note, { n ->
                    deleteShowDialog = true
                    deleteNote = n
                })
            }
        }
    }

    if (showDialog) {
        AddDialog (vm,
            { showDialog = false } )
    }

    if(deleteShowDialog){
        DeleteDialog({deleteShowDialog = false},
            deleteNote, vm)
    }

}




@Composable
fun ItemUI(note: Note,
           onDeleteNote:(deleteNote: Note) -> Unit) {
    Card(
        modifier = Modifier.height(150.dp)
            .fillMaxWidth()
            .padding(4.dp).combinedClickable(
                onLongClick = {
                   onDeleteNote(note) //lambda
                },
                onClick = {

                },
            ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD3BAEF)),

        ) {
        Box(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                Text(
                    note.title,
                    fontSize = 18.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    note.description,
                    fontSize = 16.sp,
                    maxLines = 3
                )
            }

        }
    }
}

@Composable
fun AddDialog(vm: NoteVM, onCloseDialog: () -> Unit) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = {
            //ekran qalegen jerin basqanda
            // yamasa programmadan shigip ketkende
            // nezat islew kk ekenin jazamiz
            onCloseDialog()
        },
        dismissButton = {
            //razi emespen
            Button(onClick = {
                onCloseDialog()
            }) {
                Text("Cancel")
            }
        },
        confirmButton = {
            //raziliq bildiremen
            Button(onClick = {
                vm.add(
                    Note(
                        title = title,
                        description = description
                    )
                )
                onCloseDialog()
            }) {
                Text("Add")
            }
        },
        title = {
            //dialog ui in sogamiz
            Column() {
                Text("Add user", fontSize = 18.sp)
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text("Add title for you note")
                    },
                    maxLines = 1
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    label = {
                        Text("Add title for you note")
                    },
                    maxLines = 10
                )
            }
        }
    )
}

@Composable
fun DeleteDialog(onCloseDialog: () -> Unit,
                 note: Note,
                 vm: NoteVM){
    AlertDialog(
        onDismissRequest = {
            onCloseDialog()
        },
        confirmButton = {
            Text("Confirm", fontSize = 14.sp,
                modifier = Modifier.clickable{
                    vm.deleteNote(note)
                    onCloseDialog()
            })
        },
        dismissButton = {
            Text("Cancel", fontSize = 14.sp,
                modifier = Modifier.clickable{
                    onCloseDialog()
                })
        },
        text = {
            Column() {
                Row() {
                    Icon(Icons.Default.Warning,
                        "warning",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(8.dp),
                        tint = Color.Red)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Delete note",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Are you sure for deleting this note?",
                    fontSize = 14.sp)
            }
        }
    )
}
