package com.oneasad.currencyappjetpackcompose

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import com.oneasad.currencyappjetpackcompose.compoenents.InputText
import com.oneasad.currencyappjetpackcompose.compoenents.TransparentOutLinedText
import java.text.DecimalFormat


@Composable
fun CurrencyScreen(conversionRate: Double, modifier: Modifier = Modifier) {
    var amount by remember { mutableStateOf("") }
    var convertedAmount by remember { mutableStateOf("") }

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
        )
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Currency Converter",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        TransparentOutLinedText(
            text = amount,
            onValueChange = { newValue -> amount = newValue },
            placeholder = "Amount in USD",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info"
                )
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            textHandler = {amount = it},
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            try{
                convertedAmount = converter(amount.toDouble(), conversionRate)
            }catch (e: NumberFormatException){
                showDialog = true
                convertedAmount = ""
            } },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Convert to Pkr",
                color = Color.White,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Converted Amount: $convertedAmount",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
    }
}

fun converter(amount: Double, conversionRate: Double): String{
    val result = amount * conversionRate
    return DecimalFormat("#.##").format(result)
}