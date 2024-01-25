package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListBoardCards() {

    var myList = listOf("Canada", "China", "USA", "Pakistan")


    LazyColumn {
        items(myList.size) { index ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "${myList.get(index)}"
                )
            }
        }
    }
}