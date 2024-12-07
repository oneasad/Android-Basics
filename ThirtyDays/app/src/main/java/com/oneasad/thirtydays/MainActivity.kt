package com.oneasad.thirtydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oneasad.thirtydays.model.Gallery
import com.oneasad.thirtydays.model.GalleryTopAppBar
import com.oneasad.thirtydays.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ThirtyDaysApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ThirtyDaysApp(modifier: Modifier = Modifier){
    Scaffold (
        topBar = { GalleryTopAppBar() },
        modifier = modifier.fillMaxSize()
    ){ innerPadding ->
        Gallery(
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        ThirtyDaysApp()
    }
}