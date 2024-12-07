package com.oneasad.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oneasad.tictactoe.logic.GameViewModel
import com.oneasad.tictactoe.screens.GameApp
import com.oneasad.tictactoe.screens.GameScreen
import com.oneasad.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                GameApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FinalPreview() {
    TicTacToeTheme {
        GameApp()
    }
}
