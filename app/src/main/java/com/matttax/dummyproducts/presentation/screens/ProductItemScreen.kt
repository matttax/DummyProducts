package com.matttax.dummyproducts.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.presentation.ProductViewModel
import com.matttax.dummyproducts.presentation.components.product.LoadedProductLandscape
import com.matttax.dummyproducts.presentation.components.product.LoadedProductPortrait
import com.matttax.dummyproducts.presentation.model.ProductState
import com.matttax.dummyproducts.ui.common.ErrorMessage
import com.matttax.dummyproducts.ui.common.ProgressBar

@Composable
fun ProductItemScreen(productViewModel: ProductViewModel) {
    val productState by productViewModel.productState.collectAsState()
    val configuration = LocalConfiguration.current
    when (val state = productState) {
        is ProductState.Result -> {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                LoadedProductPortrait(state.product)
            else LoadedProductLandscape(state.product)
        }
        is ProductState.Loading -> {
            ProgressBar(
                modifier = Modifier.fillMaxSize(),
                size = 100.dp
            )
        }
        is ProductState.NetworkError -> {
            ErrorMessage(
                modifier = Modifier.fillMaxSize(),
                message = state.message
            ) {
                productViewModel.refresh()
            }
        }
        is ProductState.NotFound -> {
            ErrorMessage(
                modifier = Modifier.fillMaxSize(),
                message = "Product not found"
            ) {
                productViewModel.refresh()
            }
        }
    }
}
