package com.matttax.dummyproducts.presentation.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.ui.common.StarShape
import com.matttax.dummyproducts.ui.common.Title
import kotlin.math.roundToInt

@Composable
fun PricePanel(
    currentPrice: Double,
    discountPercentage: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Title(
            modifier = Modifier.padding(horizontal = 35.dp),
            text = currentPrice.toString()
        )
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(StarShape(9, 1.15f))
                .background(color = Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = "-${discountPercentage.roundToInt()}%",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}
