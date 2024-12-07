package com.oneasad.emailloginjetpackcompose.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.oneasad.emailloginjetpackcompose.R
import com.oneasad.emailloginjetpackcompose.models.User
import com.oneasad.emailloginjetpackcompose.myviewmodels.SignInViewModel

enum class ProfileEnum{
    home, profile
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    googleSignInViewModel: SignInViewModel,
    onGoogleSignOutClick: () -> Unit = {},
) {
    val user by googleSignInViewModel.user.observeAsState()
    Box (
        modifier = modifier.fillMaxSize()
    ){
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.green_background),
            contentDescription = "App bar background",
            contentScale = ContentScale.Crop
        )
        ProfileTopBar(modifier = modifier)
        ProfileCard(
            user = user,
            onClick = onGoogleSignOutClick,
            modifier = modifier.wrapContentHeight()
        )
    }
}

@Composable
fun ProfileTopBar( modifier: Modifier = Modifier ){
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.Top)
                .padding(top = 32.dp, start = 8.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                tint = Color.White,
                contentDescription = "Arrow Back",
                modifier = Modifier.size(35.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .padding(top = 43.dp),
            text = "Profile",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.weight(1.5f))
    }
}

@Composable
fun ProfileCard(
    user: User?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 106.dp,
                bottom = 16.dp
            )
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(vertical = 24.dp),
        ) {
            ProfilePhoto(user)
            UserInfo(user)
            LogoutButton(onClick)
        }
    }
}

@Composable
fun ProfilePhoto(user: com.oneasad.emailloginjetpackcompose.models.User?) {
    Box {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        ) {
            AsyncImage(
                model = user?.photoUrl,
                contentDescription = "Avatar",
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .size(34.dp)
                .offset(y = (-4).dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF953C6).copy(alpha = 0.8f),
                            Color(0xFFB91D73).copy(alpha = 0.8f)
                        )
                    )
                )
                .align(Alignment.BottomEnd)
                .padding(8.dp)

        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Image",
                tint = Color.White
            )
        }
    }
}

@Composable
fun UserInfo(user: com.oneasad.emailloginjetpackcompose.models.User?){
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.wrapContentHeight()
    ){
        user?.let {
            ProfileItem(
                title = "Name",
                subtitle = it.name,
            )
        }
        user?.let {
            ProfileItem(
                title = "Email",
                subtitle = it.email,
            )
        }
        user?.let {
            ProfileItem(
                title = "Country",
                subtitle = it.country,
            )
        }
        user?.let {
            ProfileItem(
                title = "Subscription",
                subtitle = it.subscription,
                isLast = true
            )
        }
    }
}

@Composable
fun LogoutButton(onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.5.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFF953C6),
                    Color(0xFFB91D73)
                ),
                start = Offset.Zero,
                end = Offset.Infinite
            ),
        ),
        contentPadding = PaddingValues(0.dp),
    ) {
        Text(
            text = "Log out",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFB91D73),
            ),
        )
    }
}

@Composable
fun ProfileItem(
    title: String,
    subtitle: String,
    isLast: Boolean = false
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Blue
            )
        )
        Row {
            Text(
                text = subtitle,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFFA5A5A5)
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { },
                tint = Color.Blue
            )
        }
    }
    if (!isLast) HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        thickness = 1.dp,
        color = Color.Gray
    )
}
