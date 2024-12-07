package com.oneasad.currencyappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.oneasad.currencyappjetpackcompose.ui.theme.CurrencyAppJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyAppJetpackComposeTheme {
                CurrencyApp()
            }
        }
    }
}

@Composable
fun CurrencyApp(){
    Box {
        Image(
            painter = painterResource(id = R.drawable.green_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        CurrencyScreen(277.64)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CurrencyAppJetpackComposeTheme {
        CurrencyApp()
    }
}