package com.oneasad.guessappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.oneasad.guessappjetpackcompose.ui.theme.GuessAppJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessAppJetpackComposeTheme {
                ShapesApp()
            }
        }
    }
}

@Composable
fun ShapesApp(){
    Box {
        Image(
            painter = painterResource(id = R.drawable.green_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        GuessGameScreen()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuessAppJetpackComposeTheme {
        ShapesApp()
    }
}