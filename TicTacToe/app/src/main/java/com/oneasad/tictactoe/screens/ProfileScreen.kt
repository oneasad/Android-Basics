package com.oneasad.tictactoe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.tictactoe.logic.GameState
import com.oneasad.tictactoe.logic.GameUtils.PLAYER_O
import com.oneasad.tictactoe.logic.GameUtils.PLAYER_X
import com.oneasad.tictactoe.logic.GameViewModel
import com.oneasad.tictactoe.ui.theme.TicTacToeTheme
import kotlinx.coroutines.flow.StateFlow


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileScreen(uiState: StateFlow<GameState>, viewModel: GameViewModel) {

    val gameData = viewModel.gameData
    var firstPlayer by remember { mutableStateOf(gameData.playerOName) }
    var secondPlayer by remember { mutableStateOf(gameData.playerXName) }

    var firstWins by remember { mutableStateOf(gameData.playerOWins) }
    var secondWins by remember { mutableStateOf(gameData.playerXWins) }

    // Mutable states for player names and edit mode flags
    var isEditingFirst by remember { mutableStateOf(false) }
    var isEditingSecond by remember { mutableStateOf(false) }
    var statsEdit by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Player 1 Section
        PlayerNameSection(
            name = firstPlayer,
            isEditing = isEditingFirst,
            onEditClick = { isEditingFirst = true },
            onNameChange = { firstPlayer = it},
            onEditDone = {
                isEditingFirst = false
                gameData.playerOName = firstPlayer
            }
        )

        // Player 2 Section
        PlayerNameSection(
            name = secondPlayer,
            isEditing = isEditingSecond,
            onEditClick = { isEditingSecond = true },
            onNameChange = { secondPlayer = it },
            onEditDone = {
                isEditingSecond = false
                gameData.playerXName = secondPlayer
            }
        )

        // Placeholder for other information
        Text(text = "${firstPlayer}: ${firstWins}",
            style = MaterialTheme.typography.displayLarge
        )
        Text(text = "${secondPlayer}: ${secondWins}",
            style = MaterialTheme.typography.displayLarge
        )
        Button(
            onClick = {
                gameData.playerOWins = 0
                gameData.playerXWins = 0
                firstWins = gameData.playerOWins
                secondWins = gameData.playerXWins
            }
        ) {
            Text(text = "Reset")
        }
    }
}

@Composable
fun PlayerNameSection(
    name: String,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onEditDone: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        if (isEditing) {
            TextField(
                value = name,
                onValueChange = onNameChange,
                modifier = Modifier.width(200.dp),
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Done",
                        modifier = Modifier.clickable { onEditDone() }
                    )
                }
            )
        } else {
            Text(text = name, style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                modifier = Modifier.clickable { onEditClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val context = LocalContext.current
    val gameViewModel = GameViewModel(context)
    TicTacToeTheme {
        ProfileScreen(
            uiState = gameViewModel.uiState,
            viewModel = gameViewModel
        )
    }
}