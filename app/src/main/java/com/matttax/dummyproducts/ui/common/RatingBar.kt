package com.matttax.dummyproducts.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.matttax.dummyproducts.ui.theme.YellowBright
import com.matttax.dummyproducts.ui.theme.YellowDark

@Composable
fun StarRatingBar(
    rating: Float,
    starSize: Dp,
    starCount: Int = 5
) {
    Row {
        repeat(starCount) {
            Box(
                modifier = Modifier
                    .size(starSize)
                    .clip(StarShape())
                    .getRatingGradient(rating - it)
            )
        }
    }
}

fun Modifier.getRatingGradient(rating: Float): Modifier {
    return when {
        rating >= 1 -> background(YellowBright)
        rating <= 0 -> background(YellowDark)
        else -> background(
            brush = Brush.horizontalGradient(
                colorStops = arrayOf(
                    (rating % 1f) to YellowBright,
                    (rating % 1f) to YellowDark
                )
            )
        )
    }
}
