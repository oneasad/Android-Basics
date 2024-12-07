package com.oneasad.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.artspace.Art.Companion.getArts
import com.oneasad.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier
){
    var currentStep by remember { mutableStateOf(0) }
    val arts = getArts()
    val currentArt = arts[currentStep]
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ){
        ArtWall(
            imageResourceId = currentArt.image
        )
        Spacer(modifier = Modifier.height(16.dp))
        ArtDescription(
            title = currentArt.title,
            artist = currentArt.artist,
            year = currentArt.year
        )
        Controllers(
            prevClicked = { currentStep = getPrev(currentStep, arts.size) },
            nextClicked = { currentStep = getNext(currentStep, arts.size) }
        )
    }
}

fun getNext(currentStep: Int, size: Int): Int {
    return (currentStep + 1) % size
}

fun getPrev(currentStep: Int, size: Int): Int {
    return if (currentStep - 1 < 0) size - 1 else currentStep - 1
}

@Composable
fun ArtWall(
    imageResourceId: Int,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(id = imageResourceId),
        contentDescription = null,
        modifier = modifier
            .padding(16.dp)
            .shadow(16.dp)
            .background(Color.White)
            .padding(30.dp)
            .height(450.dp)
    )
}

@Composable
fun ArtDescription(
    modifier: Modifier = Modifier,
    title: String,
    artist: String,
    year: String
){
    Column (
        modifier = modifier
            .background(color = Color(0xFF87CEEB))
            .padding(16.dp)

    ){
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.W200
        )
        Row {
            Text(
                text = artist,
                fontWeight = FontWeight.Bold
            )
            Text(text = "($year)")
        }
    }
}

@Composable
fun Controllers(
    modifier: Modifier = Modifier,
    prevClicked: () -> Unit,
    nextClicked: () -> Unit
){
    Row (
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Button(
            onClick = prevClicked,
            modifier = modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = nextClicked,
            modifier = modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}