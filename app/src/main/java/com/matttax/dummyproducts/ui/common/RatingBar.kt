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
        rating >= 1 -> background(color = Color(0xFFF6BE00))
        rating <= 0 -> background(color = Color(0x35F6BE00))
        else -> background(
            brush = Brush.horizontalGradient(
                colorStops = arrayOf(
                    (rating % 1f) to Color(0xFFF6BE00),
                    (rating % 1f) to Color(0x35F6BE00)
                )
            )
        )
    }
}
