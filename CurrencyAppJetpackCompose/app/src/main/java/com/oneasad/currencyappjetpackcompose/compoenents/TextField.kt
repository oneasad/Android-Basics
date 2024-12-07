package com.oneasad.currencyappjetpackcompose.compoenents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputText(
    text: String,
    placeholder: String,
    keyboardOptions: KeyboardType,
    textHandler: (String) -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector
){
    TextField(
        value = text,
        onValueChange = textHandler,
        placeholder = { Text(text = placeholder, color = Color.White) },
        leadingIcon = { Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.White
        ) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0x40FFFFFF),
            unfocusedContainerColor = Color(0x40FFFFFF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardOptions),
        visualTransformation = if (keyboardOptions == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .background(Color.Transparent)
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .fillMaxWidth()
    )
}

@Composable
fun TransparentOutLinedText(
    text: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    textHandler: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    OutlinedTextField(
        value = text,
        onValueChange = textHandler,
        placeholder = { Text(text = placeholder, color = Color.White) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0x40FFFFFF),
            unfocusedContainerColor = Color(0x40FFFFFF),
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = Modifier
            .background(Color.Transparent)
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth()
    )
}