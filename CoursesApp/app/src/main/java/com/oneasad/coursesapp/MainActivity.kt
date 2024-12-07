package com.oneasad.coursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.coursesapp.data.DataSource
import com.oneasad.coursesapp.model.Topic
import com.oneasad.coursesapp.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoursesApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesApp(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 4.dp, end = 4.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)
    ) {
        items(DataSource.topics.size) { index ->
            Topic(
                DataSource.topics[index]
            )
        }
    }
}

@Composable
fun Topic(topic: Topic, modifier: Modifier = Modifier){
    Card (
        modifier = modifier
            .padding(4.dp)
            .height(70.dp)
            .width(intrinsicSize = IntrinsicSize.Min)
            .border(shape = MaterialTheme.shapes.medium,
                width = 1.dp, color = MaterialTheme.colorScheme.primary)
    ){
        Row (
            modifier = modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(id = topic.imageId),
                contentDescription = stringResource(id = topic.titleId),
                modifier = modifier
                    .fillMaxHeight()
                    .width(68.dp)
            )
            CardDesc(
                titleId = topic.titleId,
                courses = topic.courses.toString(),
                modifier = modifier
            )
        }
    }
}

@Composable
fun CardDesc(
    @StringRes titleId: Int,
    courses: String,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(16.dp, 16.dp, 16.dp, 8.dp)
    ){
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.bodyMedium
        )
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(top = 8.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.ic_grain),
                contentDescription = null,
            )
            Text(
                text = courses,
                style = MaterialTheme.typography.labelMedium,
                modifier = modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesAppTheme {
        CoursesApp()
    }
}