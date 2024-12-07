package com.oneasad.guessappjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.guessappjetpackcompose.components.InputText
import com.oneasad.guessappjetpackcompose.ui.theme.GuessAppJetpackComposeTheme


@Composable
fun GuessGameScreen(modifier: Modifier = Modifier ){
    var userGuess by remember { mutableStateOf("") }
    var randomNumber by remember { mutableIntStateOf((1..20).random()) }

    var message by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("NumberFormatException") },
            text = { Text("Enter a numeric value") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.iiui_logo_circular),
            contentDescription = "Currency Converter",
            modifier = Modifier.height(110.dp)
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Guess a Number",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        InputText(
            text = userGuess,
            placeholder = "Numeric value",
            label = "Your Guess",
            textHandler = { userGuess = it },
            imageVector = Icons.Default.Info,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            try{
                if(randomNumber == userGuess.toInt())
                    message = "Your Guess is Correct"
                else if(randomNumber > userGuess.toInt())
                    message = "Guess Higher"
                else if(randomNumber < userGuess.toInt())
                    message = "Guess Lower"
            }catch (e: NumberFormatException){
                showDialog = true
                message = ""
                userGuess = ""
            } },
            ) {
            Text("Check")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        if(message == "Your Guess is Correct"){
            Button(onClick = {
                randomNumber = (1..20).random()
                message = ""
                userGuess = ""
            }) {
                Text("Reset")
            }
        }
    }
}


@Preview
@Composable
fun GuessScreenPreview(){
    GuessAppJetpackComposeTheme {
        GuessGameScreen()
    }
}