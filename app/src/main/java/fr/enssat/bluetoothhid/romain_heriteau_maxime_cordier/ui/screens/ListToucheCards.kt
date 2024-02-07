package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import compose.icons.AllIcons
import compose.icons.FeatherIcons
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.BluetoothController
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.KeyboardSender
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.Shortcut
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.common.DialogNewTouche

data class Tile(
    var name: String,
    var command: String,
    var icon: String
)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListToucheCards(
    onPressCard: (shortcut: Shortcut, releaseModifiers: Boolean) -> Boolean,
) {
    var touches by remember {
        mutableStateOf(mutableStateListOf<Tile?>(*Array<Tile>(12) { Tile(name="", command="", icon="") }))
    }

    var openDialogNewTouche = remember { mutableStateOf(false) }
    var newTouche = remember { mutableIntStateOf(-1) }
    var allIcons = remember { FeatherIcons.AllIcons }

    fun getIcon(icon : String): ImageVector {
        allIcons.forEach {
            if(it.name == icon) {
                return it
            }
        }
        return Icons.Filled.Abc
    }

    fun addTouche(touche : Int, name : String, command : String, icon : String) {
        touches[touche] = Tile(name, command, icon)
        openDialogNewTouche.value = false
    }

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
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                }
            }
            else {
                Box(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                ) {
                    Card(
                        onClick = { Log.d("Click", "CardExample: Card Click")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        elevation = 8.dp
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                            Icon(imageVector = getIcon(touche.icon), contentDescription = "Icon", modifier = Modifier.padding(end = 12.dp))
                            Column {
                                if(touche.name == "") {
                                    Text(text = "Sans nom", fontStyle = FontStyle.Italic)
                                } else {
                                    Text(text = touche.name)
                                }
                                Text(text = touche.command)
                            }

                        }
                    }
                }
            }
        }
    }

    when {
        openDialogNewTouche.value -> {
            DialogNewTouche(
                newTouche = newTouche.value,
                icons = allIcons,
                getIcon = { icon -> getIcon(icon) },
                onDismissRequest = { openDialogNewTouche.value = false },
                onConfirmation = { touche, name, command, icon -> addTouche(touche, name, command, icon) }
            )
        }
    }
}