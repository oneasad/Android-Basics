package com.oneasad.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.lemonade.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ){innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ){
            LemonadeStep(modifier.fillMaxSize())
        }
    }
}

@Composable
fun LemonadeStep(modifier: Modifier = Modifier){
    var step by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    val (imageID, textID, imageDesc) =  getResources(step)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ){
        if(step == 2){
            Text(
                text = "Squeeze: $squeezeCount"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if(step == 2 && squeezeCount == 0){
                    squeezeCount = (2..4).random()
                }
                step = if(step == 2 && squeezeCount > 0){
                    squeezeCount--
                    if(squeezeCount <=0 )
                        changeStep(step)
                    else
                        2
                } else
                    changeStep(step)
            },
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(imageID),
                contentDescription = stringResource(imageDesc),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button_image_width))
                    .height(dimensionResource(R.dimen.button_image_height))
                    .padding(dimensionResource(R.dimen.button_interior_padding))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
        Text(
            text = stringResource(textID),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

fun changeStep(step: Int): Int{
    return when(step){
        1 -> 2
        2 -> 3
        3 -> 4
        else -> 1
    }
}

fun getResources(step: Int): Triple<Int, Int, Int>{
    return when(step){
        1 -> Triple(R.drawable.lemon_tree, R.string.tap_lemon_tree, R.string.lemon_tree_content_desc)
        2 -> Triple(R.drawable.lemon_squeeze, R.string.squeeze_lemon, R.string.lemon_content_desc)
        3 -> Triple(R.drawable.lemon_drink, R.string.drink_lemonade, R.string.lemonade_content_desc)
        else -> Triple(R.drawable.lemon_restart, R.string.start_again, R.string.empty_glass_content_desc)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        LemonadeApp()
    }
}