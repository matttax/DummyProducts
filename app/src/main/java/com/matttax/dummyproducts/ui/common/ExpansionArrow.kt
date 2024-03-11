package com.matttax.dummyproducts.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.R

@Composable
fun ExpansionArrow(isExpanded: Boolean) {
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f
    )
    Image(
        modifier = Modifier
            .size(30.dp)
            .rotate(rotationState),
        painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_down_24),
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
    )
}
