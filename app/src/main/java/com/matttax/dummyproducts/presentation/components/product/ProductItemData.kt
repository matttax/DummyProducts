package com.matttax.dummyproducts.presentation.components.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.matttax.dummyproducts.domain.model.ProductDomainModel
import com.matttax.dummyproducts.ui.common.ImagePager
import com.matttax.dummyproducts.ui.common.StarRatingBar
import com.matttax.dummyproducts.ui.common.Title

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductItemData(
    product: ProductDomainModel,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = product.title
        )
        ImagePager(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            imagesUris = product.imageUris,
            pagerState = pagerState
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            StarRatingBar(
                rating = product.rating.toFloat(),
                starSize = 30.dp
            )
            Text(
                text = "(${product.rating})",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        ProductDescription(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            spaceBetween = 16.dp,
            description = product.description,
            category = product.category
        )
    }
}

@Composable
fun ProductDescription(
    description: String,
    category: String,
    spaceBetween: Dp,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(Modifier.height(spaceBetween))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Category: ",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = category,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}