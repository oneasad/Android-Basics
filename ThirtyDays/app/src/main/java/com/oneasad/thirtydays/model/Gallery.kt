package com.oneasad.thirtydays.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.thirtydays.ui.theme.AppTheme

@Composable
fun Gallery(modifier: Modifier = Modifier){
    val posts = PostRepo().getPosts()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
    ){
        items(posts){ post ->
            GalleryItem(
                day = post.day,
                titleId = post.titleId,
                imageId = post.imageId,
                descriptionId = post.descriptionId
            )
        }

    }
}

@Composable
fun GalleryItem(
    day: Int,
    @StringRes titleId: Int,
    @DrawableRes imageId: Int,
    @StringRes descriptionId: Int,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    val color = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
    else MaterialTheme.colorScheme.primaryContainer
    //cards padding before
    //.padding( start = 16.dp, end = 16.dp, top = 8.dp)
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(color = color),
        onClick = { expanded = !expanded }
    ) {
        Column (
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessHigh
                    )
                )
        ){
            Text(
                text = "Day $day",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 6.dp)
            )
            Text(
                text = stringResource(titleId),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 6.dp, top = 8.dp, bottom = 8.dp)
            )
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .height(200.dp)
            )
            if (expanded) {
                Text(
                    text = stringResource(descriptionId),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 6.dp,top = 8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Motivational Quotes",
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GalleryItemPreview() {
    AppTheme {
        Gallery()
    }
}