package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class AppRepository(private val boardDAO: BoardDAO, private val toucheDAO: ToucheDAO) {

    val allBoard: Flow<List<Board>> = boardDAO.getAllBoard()
    val allTouche: Flow<List<Touche>> = toucheDAO.getAllTouche()

    fun getToucheByBoard(idBoard: Int): Flow<List<Touche>> {
        return toucheDAO.getToucheByBoard(idBoard)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertBoard(board: Board) {
        boardDAO.insertBoard(board)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTouche(touche: Touche) {
        toucheDAO.insertTouche(touche)
    }
}