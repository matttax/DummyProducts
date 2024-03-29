package com.matttax.dummyproducts.presentation.components.search

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.presentation.model.ProductUiModel
import com.matttax.dummyproducts.ui.common.StarRatingBar

@Composable
fun ProductItem(
    product: ProductUiModel,
    onClick: (Long) -> Unit
) {
    val configuration = LocalConfiguration.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .clickable { onClick(product.id) },
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductThumbnail(
                modifier = Modifier.weight(
                    when(configuration.orientation){
                        Configuration.ORIENTATION_PORTRAIT -> 0.4f
                        else -> 0.25f
                    }
                ),
                uri = product.thumbnailUri
            )
            Spacer(
                modifier = Modifier.weight(0.05f)
            )
            Column(
                modifier = Modifier.weight(
                    when(configuration.orientation){
                        Configuration.ORIENTATION_PORTRAIT -> 0.55f
                        else -> 0.7f
                    }
                )
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                StarRatingBar(
                    rating = product.rating,
                    starSize = 20.dp
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Text(
                    text = "In stock: ${product.inStock}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Row {
                    Text(
                        text = String.format("%.1f", product.price),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )
                    Text(
                        text = String.format("%.1f", product.oldPrice),
                        style = MaterialTheme.typography.labelMedium.copy(
                            textDecoration = TextDecoration.LineThrough
                        ),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}
