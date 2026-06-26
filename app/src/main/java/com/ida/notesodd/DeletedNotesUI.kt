package com.ida.notesodd

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun DeletedNotesUI(){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text("Empty",
            color = Color(0xFF787E83),
            fontSize = 18.sp)
    }
}