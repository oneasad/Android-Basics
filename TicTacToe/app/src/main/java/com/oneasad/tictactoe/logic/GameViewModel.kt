package com.oneasad.tictactoe.logic

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.oneasad.tictactoe.data.GameData
import com.oneasad.tictactoe.data.GameStorage
import com.oneasad.tictactoe.logic.GameUtils.PLAYER_O
import com.oneasad.tictactoe.logic.GameUtils.PLAYER_X
import com.oneasad.tictactoe.logic.GameUtils.gameResult
import com.oneasad.tictactoe.logic.GameUtils.isBoardFull
import com.oneasad.tictactoe.logic.GameUtils.isGameWon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(context: Context) : ViewModel() {
    private var _uiState = MutableStateFlow(GameState())
    var uiState: StateFlow<GameState> = _uiState.asStateFlow()

    private val gameStorage = GameStorage(context)
    var gameData: GameData = gameStorage.loadGameData()
        private set

    fun saveGameResults() {
        gameStorage.saveGameData(gameData.playerXName, gameData.playerOName, gameData.playerXWins, gameData.playerOWins)
    }

    fun play(move: Int){
        if(_uiState.value.isGameOver) return

        var board = _uiState.value.board
        val player = _uiState.value.currentPlayer

        if( board[move] == ""){
            if(player == PLAYER_X){
                _uiState.update { currentState ->
                    currentState.copy(
                        board = board.toMutableList().apply {
                            this[move] = PLAYER_X
                        },
                        currentPlayer = PLAYER_O
                    )
                }
            }
            else{
                _uiState.update { currentState ->
                    currentState.copy(
                        board = board.toMutableList().apply {
                            this[move] = PLAYER_O
                            },
                        currentPlayer = PLAYER_X
                    )
                }
            }
        }
        board = _uiState.value.board
        //calculate and show game result
        val gameStatus = isGameWon(board, PLAYER_X) ||
                isGameWon(board, PLAYER_O) ||
                isBoardFull(board)
        val gameWinner = gameResult(board)

        updateWins(gameStatus, gameWinner)

        _uiState.update { currentState ->
            currentState.copy(
                isGameOver = gameStatus,
                winner = gameWinner
            )
        }

        Log.d(TAG, "$board")
    }

    private fun updateWins(gameStatus: Boolean, gameWinner: String) {
        if (gameStatus && gameWinner == PLAYER_X) {
            gameData.playerXWins++
        } else if (gameStatus && gameWinner == PLAYER_O) {
            gameData.playerOWins++
        }
    }

    fun reset() {
        saveGameResults()
        _uiState.value = GameState()
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}