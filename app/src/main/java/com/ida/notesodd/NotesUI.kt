package com.ida.notesodd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ida.notesodd.data.Note


@Composable
fun NotesUI(notes: List<Note>) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //TODO
            }) {
                Icon(Icons.Default.Add,
                    "add icon")
            }
        }
    ) { ip ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize().padding(ip)
        ) {
            items(notes) { note ->
                ItemUI(note)
            }
        }
    }
}




@Composable
fun ItemUI(note: Note) {
    Card {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(note.title, fontSize = 18.sp, overflow = TextOverflow.Ellipsis, maxLines = 1)
                Icon(
                    Icons.Filled.Delete,
                    "delete icon",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )

            }
            Text(note.createdTime.toString())
        }
    }
}