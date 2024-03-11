package com.matttax.dummyproducts.presentation.components.product

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.R
import com.matttax.dummyproducts.presentation.model.CartCountEvent
import com.matttax.dummyproducts.presentation.utils.ui.AnimationUtils
import com.matttax.dummyproducts.ui.common.StarShape
import com.matttax.dummyproducts.ui.common.Title
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CartPanel(
    price: Double,
    discountPercentage: Double,
    inStock: Int,
    currentCount: StateFlow<Int>,
    onChangeCount: (CartCountEvent) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val count by currentCount.collectAsState()
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CounterButton(R.drawable.ic_baseline_minus_24) {
                    onChangeCount(CartCountEvent.DECREMENT)
                }
                Row {
                    Text(
                        text = count.toString(),
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = " / $inStock",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
                CounterButton(R.drawable.ic_baseline_add_24) {
                    onChangeCount(CartCountEvent.INCREMENT)
                }
            }
            AnimatedVisibility(
                visible = count > 0,
                enter = AnimationUtils.popUpEnter(AnimationUtils.PopUpDirection.UP),
                exit = AnimationUtils.popUpExit(AnimationUtils.PopUpDirection.UP)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    onClick = onSubmit
                ) {
                    Row {
                        Text(
                            text = "Add to cart",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                        Text(
                            text = " (${String.format("%.1f", price * count)})",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
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
