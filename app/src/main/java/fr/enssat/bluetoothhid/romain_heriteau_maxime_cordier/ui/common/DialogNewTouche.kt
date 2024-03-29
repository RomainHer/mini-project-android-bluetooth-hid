package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.common

import android.view.KeyEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.KeyboardReport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogNewTouche(
    newTouche: Int,
    icons: List<ImageVector>,
    getIcon: (icon : String) -> ImageVector,
    onDismissRequest: () -> Unit,
    onConfirmation: (touche : Int, name : String, command : String, icon : String) -> Unit
) {
    var toucheName by remember { mutableStateOf("") }
    var optionsIcons = mutableListOf<String>()
    icons.forEach { icon ->
        optionsIcons.add(icon.name)
    }
    var optionsCommands = mutableListOf<String>()
    KeyboardReport.KeyEventMap.keys.forEach { key ->
        optionsCommands.add(KeyEvent.keyCodeToString(key))
    }
    var expandedIcons by remember { mutableStateOf(false) }
    var expandedCommands by remember { mutableStateOf(false) }
    var selectedOptionIcon by remember { mutableStateOf(optionsIcons[0]) }
    var selectedOptionCommand by remember { mutableStateOf(optionsCommands[0]) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Ajouter une nouvelle touche",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                )
                TextField(
                    value = toucheName,
                    onValueChange = { toucheName = it },
                    label = { Text("Nom de la touche") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                ExposedDropdownMenuBox(
                    expanded = expandedIcons,
                    onExpandedChange = { expandedIcons = it },
                    modifier = Modifier.padding(16.dp),
                ) {
                    TextField(
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = selectedOptionIcon,
                        onValueChange = {},
                        label = { Text("Icon") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedIcons)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = expandedIcons,
                        onDismissRequest = { expandedIcons = false },
                    ) {
                        optionsIcons.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Icon(imageVector = getIcon(selectionOption), contentDescription = "Icon")},
                                onClick = {
                                    selectedOptionIcon = selectionOption
                                    expandedIcons = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                ExposedDropdownMenuBox(
                    expanded = expandedCommands,
                    onExpandedChange = { expandedCommands = it },
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    TextField(
                        // The `menuAnchor` modifier must be passed to the text field for correctness.
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = selectedOptionCommand,
                        onValueChange = {},
                        label = { Text("Commande") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCommands) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = expandedCommands,
                        onDismissRequest = { expandedCommands = false },
                    ) {
                        optionsCommands.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    selectedOptionCommand = selectionOption
                                    expandedCommands = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation(newTouche, toucheName, selectedOptionCommand, selectedOptionIcon) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

/*
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
var expanded by remember { mutableStateOf(false) }
var selectedOptionText by remember { mutableStateOf(options[0]) }
// We want to react on tap/press on TextField to show menu
ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = it },
) {
    TextField(
        readOnly = true,
        value = selectedOptionText,
        onValueChange = { },
        label = { Text("Label") },
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(
                expanded = expanded
            )
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors()
    )
    ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        }
    ) {
        options.forEach { selectionOption ->
            DropdownMenuItem(
                onClick = {
                    selectedOptionText = selectionOption
                    expanded = false
                }
            ) {
                Text(text = selectionOption)
            }
        }
    }
}
 */