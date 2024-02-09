package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BoardViewModel(private val repository: AppRepository) : ViewModel() {

    val allBoard: LiveData<List<Board>> = repository.allBoard.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertBoard(board: Board) = viewModelScope.launch {
        repository.insertBoard(board)
    }
}