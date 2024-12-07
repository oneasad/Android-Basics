package com.oneasad.tictactoe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.oneasad.tictactoe.screens.ScreenType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Tic Tac Toe",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun GameBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = {
                    if (currentRoute != ScreenType.Game.name)
                        navController.navigate(ScreenType.Game.name)
                }
            ) {
                Text(
                    text = "Game",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            TextButton(
                onClick = {
                    if (currentRoute != ScreenType.Profile.name)
                        navController.navigate(ScreenType.Profile.name)
                }
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            TextButton(
                onClick = {
                    if (currentRoute != ScreenType.About.name)
                        navController.navigate(ScreenType.About.name)
                }
            ) {
                Text(
                    text = "About",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}