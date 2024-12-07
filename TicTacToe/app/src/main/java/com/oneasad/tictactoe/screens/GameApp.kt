package com.oneasad.tictactoe.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oneasad.tictactoe.components.GameBottomBar
import com.oneasad.tictactoe.components.GameTopBar
import com.oneasad.tictactoe.logic.GameViewModel

enum class ScreenType{
    Game,
    Profile,
    About
}

@Composable
fun GameApp() {
    val context = LocalContext.current
    val gameViewModel = GameViewModel(context)
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntry
    val currentScreen = ScreenType.valueOf(
        backStackEntry?.destination?.route ?: ScreenType.Game.name
    )

    Scaffold(
        topBar = { GameTopBar() },
        bottomBar = { GameBottomBar(navController) },
        containerColor = MaterialTheme.colorScheme.background
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenType.Game.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(ScreenType.Game.name){
                GameScreen(gameViewModel)
            }
            composable(ScreenType.Profile.name){
                ProfileScreen(uiState = gameViewModel.uiState, viewModel = gameViewModel)
            }
            composable(ScreenType.About.name){
                BusinessCard()
            }
        }
    }
}
