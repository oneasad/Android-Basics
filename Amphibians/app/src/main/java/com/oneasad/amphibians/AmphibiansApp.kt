package com.oneasad.amphibians

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oneasad.amphibians.ui.AmphibiansViewModel
import com.oneasad.amphibians.ui.screens.AmphibianTopAppBar
import com.oneasad.amphibians.ui.screens.HomeScreen

@Composable
fun AmphibiansApp() {
    Scaffold(
        topBar = { AmphibianTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) {
        Surface (
            modifier = Modifier.fillMaxSize()
        ){
            val amphibiansViewModel: AmphibiansViewModel =
                viewModel(factory = AmphibiansViewModel.Factory)
            HomeScreen(
                amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                retryAction = amphibiansViewModel::getAmphibians,
                contentPadding = it
            )
        }
    }
}