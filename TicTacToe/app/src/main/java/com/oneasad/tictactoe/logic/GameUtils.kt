package com.oneasad.tictactoe.logic

object GameUtils {
    var PLAYER_X = "X"
    var PLAYER_O = "O"

    /**
     * Determine if the board is full
     * */
    fun isBoardFull(board: List<String>): Boolean {
        for (i in board) {
            if (i != PLAYER_X && i != PLAYER_O) return false
        }
        return true
    }

    /**
     * Determines if the game is won
     * */
    fun isGameWon(board: List<String>, player: String): Boolean =
        //check rows
        if (board[0] == player && board[1] == player && board[2] == player) true
        else if (board[3] == player && board[4] == player && board[5] == player) true
        else if (board[6] == player && board[7] == player && board[8] == player) true

        //check columns
        else if (board[0] == player && board[3] == player && board[6] == player) true
        else if (board[1] == player && board[4] == player && board[7] == player) true
        else if (board[2] == player && board[5] == player && board[8] == player) true

        //check diagonals
        else if (board[2] == player && board[4] == player && board[6] == player) true
        else board[0] == player && board[4] == player && board[8] == player

    /**
     * Returns a readable game won text
     * */
    fun gameResult(board: List<String>): String {
        when {
            isGameWon(board, PLAYER_X) -> return PLAYER_X
            isGameWon(board, PLAYER_O) -> return PLAYER_O
            isBoardFull(board) -> return "It is Tie"
        }
        return "Tie"
    }
}