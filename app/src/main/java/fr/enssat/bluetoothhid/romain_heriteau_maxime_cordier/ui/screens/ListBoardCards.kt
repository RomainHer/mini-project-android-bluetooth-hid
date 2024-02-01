package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun ListBoardCards(navigateToBoard: (String) -> Unit) {
    var boards by remember { mutableStateOf(listOf("Jeux", "Travail", "Raccourcis")) }
    var inputNewBoard by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current


    fun addNewBoard() {
        if(inputNewBoard != "") {
            boards = listOf(inputNewBoard) + boards
            inputNewBoard = ""

            keyboardController?.hide()
        }
    }

    Column {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = inputNewBoard,
                onValueChange = { inputNewBoard = it },
                label = { Text("Ajouter un Board") }
            )
            FloatingActionButton(
                onClick = { addNewBoard() },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
        LazyColumn {
            items(boards) { board ->
                Card(
                    onClick = { navigateToBoard(board) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = board,
                        color = Color.White
                    )
                }
            }
        }
    }
}