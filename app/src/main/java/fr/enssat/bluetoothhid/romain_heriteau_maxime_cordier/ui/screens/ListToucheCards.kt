package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.common.DialogNewTouche
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.theme.SimpleNavComposeAppTheme

data class Tile(
    var name: String,
    var command: String,
    var icon: String
)
@Composable
fun ListToucheCards() {
    var touches by remember {
        mutableStateOf(mutableStateListOf<Tile?>(*Array<Tile>(12) { Tile(name="", command="", icon="") }))
    }

    var openDialogNewTouche = remember { mutableStateOf(false) }
    var newTouche = remember { mutableIntStateOf(-1) }
    var myList = listOf("Canada", "China", "USA", "Pakistan", "France", "Japan", "England")

    var showCountrySelection by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<String?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(touches) { touche ->
            if(touche == null || touche.icon == "" && touche.name == "" && touche.command == "") {
                Box(modifier = Modifier.padding(15.dp)) {
                    FloatingActionButton(
                        onClick = { newTouche.value = touches.indexOf(touche); openDialogNewTouche.value = true },
                        modifier = Modifier.wrapContentSize().align(Alignment.Center)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                }
            }
        }
        /*items(myList.size + 1) { index ->
            if (index < myList.size) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${myList[index]}"
                        )
                    }
                }
            } else {
                // Carte "PLUS" pour la sélection du pays
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable {
                            showCountrySelection = true
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "PLUS",
                        )
                    }
                }
            }
        }*/
    }

    when {
        openDialogNewTouche.value -> {
            DialogNewTouche(
                newTouche = newTouche.value,
                onDismissRequest = { openDialogNewTouche.value = false },
                onConfirmation = { openDialogNewTouche.value = false }
            )
        }
    }

    /*
    // Afficher la liste des pays ou le pays sélectionné en fonction de l'état
    if (showCountrySelection) {
        CountrySelectionList(
            countries = myList,
            onCountrySelected = { country ->
                selectedCountry = country
                showCountrySelection = false
            }
        )
    } else if (selectedCountry != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectedCountry!!,
                )
            }
        }
    }
    */
}

@Composable
fun CountrySelectionList(countries: List<String>, onCountrySelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Sélectionnez un pays",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(countries) { country ->
                CountrySelectionItem(
                    country = country,
                    onCountrySelected = onCountrySelected
                )
            }
        }
    }
}

@Composable
fun CountrySelectionItem(country: String, onCountrySelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onCountrySelected(country)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = country,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SimpleNavComposeAppTheme(useSystemUiController = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            ListToucheCards(
            )
        }
    }
}