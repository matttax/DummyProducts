package com.matttax.dummyproducts.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProductThumbnail(
    modifier: Modifier,
    uri: String
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(shape = RoundedCornerShape(15.dp)),
            model = uri,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}
