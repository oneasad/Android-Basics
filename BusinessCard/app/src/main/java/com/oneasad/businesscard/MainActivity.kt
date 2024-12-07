package com.oneasad.businesscard

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oneasad.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

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

//@Composable
//fun BusinessCard(
//    modifier: Modifier = Modifier
//) {
//    Column (
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .fillMaxSize()
//    ){
//        ProfileSection()
//        Spacer(modifier = modifier.padding(8.dp))
//        ContactDetails(
//            modifier = modifier
//        )
//    }
//}

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
            modifier = modifier
                .padding(top = 2.dp)
        )
        ContactRow(
            imageVector = Icons.Default.Share,
            contentDescription = Icons.Default.Share.name,
            data = "@AndroidDeveloper",
            modifier = modifier
                .padding(top = 2.dp)
        )
        ContactRow(
            imageVector = Icons.Default.Email,
            contentDescription = Icons.Default.Email.name,
            data = "oneasad@gmail.com",
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
    modifier: Modifier = Modifier
){
    Row {
       Icon(
           painter = rememberVectorPainter(imageVector),
           contentDescription = contentDescription,
           tint = Color(0xFF3ddc84)
       )
        Spacer(modifier = modifier.width(1.dp))
        Text(
            text = data,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}