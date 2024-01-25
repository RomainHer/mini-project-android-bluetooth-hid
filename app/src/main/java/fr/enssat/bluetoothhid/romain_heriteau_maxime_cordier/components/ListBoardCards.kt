package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListBoardCards() {
    ListItem(
        headlineContent = { Text("One line list item with 24x24 icon") },
        leadingContent = {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
            )
        }
    )
}