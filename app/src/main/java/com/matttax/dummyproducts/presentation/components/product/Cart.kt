package com.matttax.dummyproducts.presentation.components.product

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.R
import com.matttax.dummyproducts.presentation.utils.ui.AnimationUtils
import com.matttax.dummyproducts.ui.common.StarShape
import com.matttax.dummyproducts.ui.common.Title

@Composable
fun CartPanel(
    price: Double,
    discountPercentage: Double,
    inStock: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            PricePanel(
                modifier = Modifier.fillMaxWidth(),
                currentPrice = price,
                discountPercentage = discountPercentage
            )
            CartCounter(
                modifier = Modifier.fillMaxWidth(),
                max = inStock,
                perOne = price
            )
        }
    }
}

@Composable
fun CartCounter(
    min: Int = 0,
    max: Int = Int.MAX_VALUE,
    perOne: Double = 0.0,
    modifier: Modifier = Modifier
) {
    var currentCount by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CounterButton(R.drawable.ic_baseline_minus_24) {
                currentCount = (currentCount - 1).coerceIn(min, max)
            }
            Row {
                Text(
                    text = currentCount.toString(),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = " / $max",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            CounterButton(R.drawable.ic_baseline_add_24) {
                currentCount = (currentCount + 1).coerceIn(min, max)
            }
        }
        AnimatedVisibility(
            visible = currentCount > 0,
            enter = AnimationUtils.popUpEnter(AnimationUtils.PopUpDirection.UP),
            exit = AnimationUtils.popUpExit(AnimationUtils.PopUpDirection.UP)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                onClick = {}
            ) {
                Row {
                    Text(
                        text = "Add to cart",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                    Text(
                        text = " (${String.format("%.1f", perOne * currentCount)})",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        }
    }
}

@Composable
fun CounterButton(
    resourceId: Int,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(resourceId),
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null
        )
    }
}

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
                .rotate(25f)
                .clip(StarShape(9, 1.5f))
                .background(color = Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(35.dp),
                text = "-${discountPercentage}%",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}
