package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Touche (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "id_board") val idBoard: Int,
    val name: String?,
    val command: String?,
    val icon: String?
)