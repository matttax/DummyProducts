package com.matttax.dummyproducts.presentation.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import com.matttax.dummyproducts.domain.ProductDomainModel

@Composable
fun LoadedProductPortrait(product: ProductDomainModel) {
    var barYPosition by remember { mutableStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        bottomBar = {
            CartPanel(
                modifier = Modifier
                    .fillMaxWidth()
                    .onPlaced {
                        barYPosition = it.size.height
                    },
                price = product.price,
                discountPercentage = product.discountPercentage,
                inStock = product.inStock
            )
        }
    ) {
        ProductItemData(
            modifier = Modifier.padding(
                bottom = with(LocalDensity.current) { barYPosition.toDp() }
            ),
            product = product
        )
    }
}

@Composable
fun LoadedProductLandscape(product: ProductDomainModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
    ) {
        ProductItemData(product, Modifier.weight(0.55f))
        Column(
            Modifier
                .weight(0.45f)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            CartPanel(
                price = product.price,
                discountPercentage = product.discountPercentage,
                inStock = product.inStock
            )
        }
    }
}
