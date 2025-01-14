package com.oneasad.videotry3.screens.components

import android.os.Build
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.videotry3.R
import com.oneasad.videotry3.model.VideoFile
import com.oneasad.videotry3.toFormattedSize
import com.oneasad.videotry3.toFormattedTime
import com.oneasad.videotry3.ui.theme.VideoTry3Theme

@Composable
fun VideoItem(
    videoFile: VideoFile,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Column (
        modifier = modifier.clickable { onClick() }
    ){
        Row(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
        ){
            Card(
                modifier = Modifier
                    .width(180.dp)
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight()
                ){
                    val thumbnail = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        LocalContext.current.contentResolver.loadThumbnail(
                            videoFile.pathUri,
                            Size(640, 488),
                            null
                        )
                    } else {
                        null
                    }
                    if(thumbnail != null){
                        Image(
                            bitmap = thumbnail.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.image_placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    Text(
                        text = videoFile.duration.toFormattedTime(),
                        color = Color.White,
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Black)
                            .padding(horizontal = 4.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
            }
            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ){
                Text(
                    text = videoFile.name,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = videoFile.size.toFormattedSize(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        HorizontalDivider()
    }
}
