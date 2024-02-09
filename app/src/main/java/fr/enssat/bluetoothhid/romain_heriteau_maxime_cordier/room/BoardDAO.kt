package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoard(board: Board)

    @Delete
    fun deleteBoard(board: Board)

    @Query("SELECT * FROM board")
    fun getAllBoard(): Flow<List<Board>>
}