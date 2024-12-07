package com.oneasad.shapesappjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oneasad.shapesappjetpackcompose.components.InputText


@Composable
fun ShapesScreen(
    modifier: Modifier = Modifier
){
    var number by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var shapes = mutableListOf<Int>()

    when {
        result == ("$number is both Triangular and Square.") -> {
            shapes.add(R.drawable.triangle_shape)
            shapes.add(R.drawable.square_shape)
        }
        result == ("$number is Triangular.") -> shapes.add(R.drawable.triangle_shape)
        result == ("$number is Square.") -> shapes.add(R.drawable.square_shape)
        else -> shapes.clear()
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.iiui_logo_circular),
            contentDescription = "Currency Converter"
        )
        Spacer(modifier = Modifier.height(100.dp))

        if (shapes.isNotEmpty()) {
            ShapesGrid(shapes = shapes)
        }
        Text(
            text = result,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        InputText(
            text = number,
            placeholder = "Numeric value",
            textHandler = { number = it },
            imageVector = Icons.Default.Info,
            keyboardType = KeyboardType.Text,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = { result = getResult(number.toInt())}
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun ShapesGrid(
    modifier: Modifier = Modifier,
    shapes: MutableList<Int>
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .padding(bottom = 16.dp)
    ){
        for (shape in shapes) {
            Image(
                painter = painterResource(id = shape),
                contentDescription = null
            )
        }
    }
}

fun isTriangular(number: Int): Boolean {
    var sum = 0
    var n = 1
    while (sum <= number) {
        sum += n
        if (sum == number) {
            return true
        }
        n++
    }
    return false
}

fun isSquare(number: Int): Boolean {
    val sqrt = kotlin.math.sqrt(number.toDouble()).toInt()
    if (sqrt * sqrt == number)
        return true
    else
        return false
}

fun getResult(number: Int): String{
    val triangular = isTriangular(number)
    val square = isSquare(number)

    when {
        triangular && square -> return ("$number is both Triangular and Square.")
        triangular -> return ("$number is Triangular.")
        square -> return ("$number is Square.")
        else -> return ("$number is neither Triangular nor Square.")
    }
}


