package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Board (
    @PrimaryKey val id: Int,
    val name: String?
)