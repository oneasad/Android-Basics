package com.oneasad.tictactoe.screens

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.tictactoe.logic.GameViewModel
import com.oneasad.tictactoe.R
import com.oneasad.tictactoe.data.GameData
import com.oneasad.tictactoe.logic.GameState
import com.oneasad.tictactoe.logic.GameUtils.PLAYER_O
import com.oneasad.tictactoe.logic.GameUtils.PLAYER_X
import com.oneasad.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
){
    val uiState by viewModel.uiState.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ){
        GridImage()
        ButtonGrid(uiState.board ,viewModel::play, uiState)
    }
    if(uiState.isGameOver){
        uiState.winner.let {
            FinalScoreDialog(
                viewModel = viewModel,
                gameData = viewModel.gameData,
                winner = it,
                onPlayAgain = { viewModel.reset() }
            )
        }
    }
}

@Composable
fun GridImage() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.grid_264),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ButtonGrid(board: List<String>, onClick: (Int) -> Unit, uiState: GameState) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            ImageButton(text = board[0]){ onClick(0) }
            Spacer(modifier = Modifier.width(20.dp))
            ImageButton(text = board[1]){ onClick(1) }
            Spacer(modifier = Modifier.width(20.dp))
            ImageButton(text = board[2]){ onClick(2) }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            ImageButton(text = board[3]){ onClick(3) }
            Spacer(modifier = Modifier.width(20.dp))
            ImageButton(text = board[4]){ onClick(4) }
            Spacer(modifier = Modifier.width(20.dp))
            ImageButton(text = board[5]){ onClick(5) }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            ImageButton(text = board[6]){ onClick(6) }
            Spacer(modifier = Modifier.width(20.dp))
            ImageButton(text = board[7]){ onClick(7) }
            Spacer(modifier = Modifier.width(20.dp))
            ImageButton(text = board[8]){ onClick(8) }
        }
    }
}

@Composable
fun ImageButton(
    text: String,
    onClick: () -> Unit = {},
){
    val imageId: Int = when(text){
        PLAYER_O -> R.drawable.tick
        PLAYER_X -> R.drawable.cross
        else -> R.drawable.white
    }

    Button(
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .size(110.dp)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        )
    }
}

@Composable
private fun FinalScoreDialog(
    winner: String,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier,
    gameData: GameData,
    viewModel: GameViewModel
) {
    val activity = (LocalContext.current as Activity)
    val winnerName = when (winner){
        PLAYER_O -> gameData.playerOName
        PLAYER_X -> gameData.playerXName
        else -> "no"
    }
        if (winner == PLAYER_X)  gameData.playerXName
    else gameData.playerOName
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = "Game Over") },
        text = {
            if(winnerName == "no")
                Text(text = "It's a tie!")
            else
                Text(text = "$winnerName wins!")
               },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.saveGameResults()
                    activity.finish()
                }
            ) {
                Text(text = "Exit")
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = "Play Again")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun HellPreview() {
    val context = LocalContext.current
    TicTacToeTheme {
        GameScreen(GameViewModel(context))
    }
}
