package com.ida.notesodd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.ida.notesodd.dao.NoteDb
import com.ida.notesodd.data.Note
import com.ida.notesodd.ui.theme.NotesOddTheme
import com.ida.notesodd.vm.DeletedVM
import com.ida.notesodd.vm.NoteVM
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val vm: NoteVM by viewModel()
    private val deletedVM by viewModel<DeletedVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Migration - db
        setContent {
            NotesOddTheme {
                UI()
            }
        }

        deletedVM.deletePermanently()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UI(){

        val drawerState = rememberDrawerState(
            initialValue = DrawerValue.Closed
        ) //Drawer Menu ashiq ili jawiq ekenin basqaradi

        val drawerScope = rememberCoroutineScope()

        val navController = rememberNavController()

        var selected by remember {
            mutableIntStateOf(1)
        }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerTonalElevation = 4.dp
                ) {
                    NavigationDrawerItem(
                        selected = selected == 1,
                        onClick = {
                            navController.navigate("notes")
                            drawerScope.launch {
                                drawerState.close()
                            }
                            selected = 1
                        },
                        label = {
                            Text("Notes")
                        },
                        icon = {
                            Icon(Icons.Default.TextFields,
                                null)
                        }
                    )

                    NavigationDrawerItem(
                        selected = selected == 2,
                        onClick = {
                            navController.navigate("deleted"){
                                popUpTo(navController.graph.startDestinationId)
                            }
                            drawerScope.launch {
                                drawerState.close()
                            }
                            selected = 2
                        },
                        label = {
                            Text("Deleted Notes")
                        },
                        icon = {
                            Icon(Icons.Default.Delete,
                                null)
                        }
                    )
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Notes")
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    drawerScope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Menu,
                                    "menu")
                            }
                        }
                    )
                }
            ) { iP ->
                NavHost(navController = navController,
                    startDestination = "notes",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(iP)){

                    composable("notes"){
                        NotesUI(vm)
                    }
                    composable("deleted") {
                        TrashBin(deletedVM)
                    }
                }
            }
        }

    }


}

