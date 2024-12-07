package com.oneasad.tictactoe.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oneasad.tictactoe.R
import com.oneasad.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun BusinessCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF073042))
    ) {
        // Center the ProfileSection
        ProfileSection(
            modifier = Modifier.align(Alignment.Center)
        )

        // Place ContactDetails at the bottom
        ContactDetails(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .background(color = Color(0xFF5071A2))
        )
        Text(
            text = "M Asad Bashir",
            color = Color.White,
            fontSize = 34.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 8.dp)
        )
        Text(
            text = "Android Developer Extraordinaire",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3ddc84),
            modifier = modifier
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun ContactDetails(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 80.dp)
    ){
        ContactRow(
            imageVector = Icons.Default.Phone,
            contentDescription = Icons.Default.Phone.name,
            data = "+92-307-9894102",
            link = "https://www.linkedin.com/in/oneasad",
            modifier = modifier
                .padding(top = 2.dp)
        )
        ContactRow(
            imageVector = Icons.Default.Share,
            contentDescription = Icons.Default.Share.name,
            data = "@AndroidDeveloper",
            link = "https://g.dev/oneAsad",
            modifier = modifier
                .padding(top = 2.dp)
        )
        ContactRow(
            imageVector = Icons.Default.Email,
            contentDescription = Icons.Default.Email.name,
            data = "oneasad@gmail.com",
            link = "https://github.com/oneasad",
            modifier = modifier
                .padding(top = 2.dp)
        )
    }
}

@Composable
fun ContactRow(
    imageVector: ImageVector,
    contentDescription: String?,
    data: String,
    link: String = "",
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Icon(
            painter = rememberVectorPainter(imageVector),
            contentDescription = contentDescription,
            tint = Color(0xFF3ddc84)
        )
        Spacer(modifier = modifier.width(1.dp))
        ClickableText(
            text = AnnotatedString(data),
            style = androidx.compose.ui.text.TextStyle(
                Color.White,
                fontSize = 17.sp
                )
        ) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme() {
        BusinessCard()
    }
}