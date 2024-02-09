package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToucheDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTouche(touche: Touche)

    @Delete
    fun deleteTouche(touche: Touche)

    @Query("SELECT * FROM touche")
    fun getAllTouche(): Flow<List<Touche>>

    @Query("SELECT * FROM touche WHERE id_board = :idBoard")
    fun getToucheByBoard(idBoard: Int): Flow<List<Touche>>
}