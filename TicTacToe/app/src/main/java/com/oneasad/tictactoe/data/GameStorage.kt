package com.oneasad.tictactoe.data

import android.content.Context
import android.content.SharedPreferences

class GameStorage(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("TicTacToePrefs", Context.MODE_PRIVATE)

    fun saveGameData(playerXName: String, playerOName: String, playerXWins: Int, playerOWins: Int) {
        with(sharedPreferences.edit()) {
            putString("playerXName", playerXName)
            putString("playerOName", playerOName)
            putInt("playerXWins", playerXWins)
            putInt("playerOWins", playerOWins)
            apply() // Asynchronously saves the changes
        }
    }

    fun loadGameData(): GameData {
        val playerXName = sharedPreferences.getString("playerXName", "Player 2")!!
        val playerOName = sharedPreferences.getString("playerOName", "Player 1")!!
        val playerXWins = sharedPreferences.getInt("playerXWins", 0)
        val playerOWins = sharedPreferences.getInt("playerOWins", 0)
        return GameData(playerXName, playerOName, playerXWins, playerOWins)
    }
}

data class GameData(
    var playerXName: String,
    var playerOName: String,
    var playerXWins: Int,
    var playerOWins: Int
)
