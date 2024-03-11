package com.matttax.dummyproducts.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePager(
    imagesUris: List<String>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            modifier = modifier,
            count = imagesUris.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { page ->
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }.background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = imagesUris[page],
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
        HorizontalPagerIndicator(
            modifier = Modifier.padding(15.dp),
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.onPrimary,
            inactiveColor = MaterialTheme.colorScheme.onSecondary
        )
    }
}
