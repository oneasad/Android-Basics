package com.oneasad.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.currencyconverter.ui.theme.CurrencyConverterTheme
import java.text.DecimalFormat
import com.oneasad.currencyconverter.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrencyApp(277.64, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview
@Composable
fun CurrencyAppPreview() {
    CurrencyApp(277.64)
}

@Composable
fun CurrencyApp(conversionRate: Double, modifier: Modifier = Modifier) {
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
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.currency_converter), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

//        TextField(
//            value = amount,
//            onValueChange = { amount = it }
//
//        )

        OutlinedTextField(
            value = amount,
            onValueChange = { newValue -> amount = newValue },
            label = { Text(stringResource(R.string.amount_in_usd)) },
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
                convertedAmount = converter(amount.toDouble(), conversionRate)
            }catch (e: NumberFormatException){
                showDialog = true
                convertedAmount = ""
            } },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.convert_to_pkr))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Converted Amount: $convertedAmount",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

fun converter(amount: Double, conversionRate: Double): String{
    val result = amount * conversionRate
    return DecimalFormat("#.##").format(result)
}