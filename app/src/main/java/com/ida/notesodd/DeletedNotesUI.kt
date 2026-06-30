package com.ida.notesodd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ida.notesodd.data.Deleted

@Composable
fun TrashBin(){
    //vm
    //30 kun 5 minut coroutines
    //Thread  UI thread background thread
    //UI thread backgroud 0 Android
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item(){
            Text("Deleted")
        }
        items(listOf("UMida", "Shukirjan", "Perizat")){

        }
    }

    @Composable
    fun TrashBinItem(deleted: Deleted){
        Card(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(deleted.note.title, fontSize = 14.sp)
                Text(deleted.deletedTime.toString(), fontSize = 14.sp)
            }
        }
    }
}