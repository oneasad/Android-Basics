package com.example.unscramble.ui

data class GameUiState(
    val currentWordCount: Int = 1,
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false,
    val score: Int = 0,
)
