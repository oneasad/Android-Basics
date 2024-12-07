package com.oneasad.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.oneasad.amphibians.R
import com.oneasad.amphibians.model.Amphibian
import com.oneasad.amphibians.ui.AmphibiansUiState
import com.oneasad.amphibians.ui.theme.AmphibiansTheme
import kotlin.reflect.KFunction0


@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    when(amphibiansUiState){
        is AmphibiansUiState.Loading -> LoadingScreen(modifier)
        is AmphibiansUiState.Success -> AmphibiansList(
            amphibians = amphibiansUiState.amphibians,
            modifier = modifier.fillMaxSize(),
            contentPadding = contentPadding
        )
        is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "loading"
    )
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = "Failed to Load", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun AmphibiansList(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    amphibians: List<Amphibian>,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding
    ){
        items(amphibians){
            AmphibianCard(amphibian = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianTopAppBar(){
    TopAppBar(
        title = {
            Text(
                text = "Amphibians",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.W600
            )
                },
        modifier = Modifier.fillMaxWidth(),
        actions = {}
    )
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
){
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Text(
            text = "${amphibian.name}(${amphibian.type})",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp)
        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(amphibian.imgSrc)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = amphibian.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = amphibian.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibianCardPreview(){
    AmphibiansTheme {

    }
}