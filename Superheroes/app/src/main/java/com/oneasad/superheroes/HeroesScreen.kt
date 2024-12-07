package com.oneasad.superheroes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oneasad.superheroes.model.HeroesRepository
import com.oneasad.superheroes.ui.theme.SuperheroesTheme

@Composable
fun HeroList(
    modifier: Modifier = Modifier
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(HeroesRepository.heroes){ hero ->
            HeroesScreen(
                nameId = hero.nameRes,
                descriptionId = hero.descriptionRes,
                imageId = hero.imageRes,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }

}

@Composable
fun HeroesScreen(
    @StringRes nameId: Int,
    @StringRes descriptionId: Int,
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ){
            HeroText(
                nameId = nameId,
                descriptionId = descriptionId,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(16.dp))
            HeroImage(
                imageId = imageId,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun HeroText(
    @StringRes nameId: Int,
    @StringRes descriptionId: Int,
    modifier: Modifier = Modifier
){
    Column (modifier = modifier){
        Text(
            text = stringResource(id = nameId),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = descriptionId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroImage(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
    ){
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth
        )
    }
}
@Preview
@Composable
fun HeroesScreenPreview() {
    SuperheroesTheme {
        HeroList()
    }
}