package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room.AppDatabase
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room.AppRepository
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room.Board
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room.BoardDAO
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room.BoardViewModel
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room.ToucheDAO
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.common.DefaultButton
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.theme.SimpleNavComposeAppTheme

@Composable
fun HomeScreen(
    navigateToBluetooth: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToBoard: (String) -> Unit,
    appDatabase: AppDatabase
) {
    var appRepository = AppRepository(boardDAO = appDatabase.boardDao(), toucheDAO = appDatabase.toucheDao())
    var boardViewModel = BoardViewModel(repository = appRepository)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DefaultButton(
                    text = "Bluetooth",
                    onClick = { navigateToBluetooth() }
                )

                DefaultButton(
                    text = "Home",
                    onClick = { navigateToHome() }
                )
            }

            Spacer(modifier = Modifier
                .height(1.dp)
                .background(Color.White)
                .padding(top = 5.dp))

            Text("Home", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            /*Column {
                boardViewModel.allBoard.value?.forEach { board ->
                    board.name?.let {
                        DefaultButton(
                            text = it,
                            onClick = { navigateToBoard(board.name) }
                        )
                    }
                }
            }

            DefaultButton(
                text = "Ajouter un Board",
                onClick = { boardViewModel.insertBoard(Board(id = 1, name = "balbal")) }
            )*/

            ListBoardCards(navigateToBoard = navigateToBoard)
        }
    }
}

/*@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SimpleNavComposeAppTheme(useSystemUiController = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            HomeScreen(
                navigateToBluetooth = {},
                navigateToHome = {},
                navigateToBoard = {}
            )
       }
    }
}*/