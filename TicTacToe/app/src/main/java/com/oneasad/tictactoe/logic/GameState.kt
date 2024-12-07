package com.oneasad.tictactoe.logic

import com.oneasad.tictactoe.logic.GameUtils.PLAYER_O

data class GameState(
    var isGameOver: Boolean = false,
    var winner: String = "",
    var board: List<String> = listOf("", "", "", "", "", "", "", "", ""),
    var currentPlayer: String = PLAYER_O,
)
